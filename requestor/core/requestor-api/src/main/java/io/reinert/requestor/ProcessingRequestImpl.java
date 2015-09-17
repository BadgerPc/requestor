/*
 * Copyright 2015 Danilo Reinert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.reinert.requestor;

import java.util.ArrayList;
import java.util.List;

import io.reinert.requestor.auth.Auth;
import io.reinert.requestor.header.Header;
import io.reinert.requestor.uri.Uri;

public class ProcessingRequestImpl<T> implements StackableProcessingRequest {

    private final MutableRequest request;
    private final RequestDispatcher dispatcher;
    private final Deferred<T> deferred;
    private final Class<T> resolveType;
    private final Class<?> parametrizedType;
    private final List<ProcessCallback> processingStack = new ArrayList<ProcessCallback>();

    public ProcessingRequestImpl(MutableRequest request, RequestDispatcher dispatcher, Deferred<T> deferred, Class<T>
            resolveType, Class<?> parametrizedType) {
        this.request = request;
        this.dispatcher = dispatcher;
        this.deferred = deferred;
        this.resolveType = resolveType;
        this.parametrizedType = parametrizedType;
    }

    @Override
    public String getAccept() {
        return request.getAccept();
    }

    @Override
    public String getContentType() {
        return request.getContentType();
    }

    @Override
    public Headers getHeaders() {
        return request.getHeaders();
    }

    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }

    @Override
    public HttpMethod getMethod() {
        return request.getMethod();
    }

    @Override
    public Object getPayload() {
        return request.getPayload();
    }

    @Override
    public int getTimeout() {
        return request.getTimeout();
    }

    @Override
    public Uri getUri() {
        return request.getUri();
    }

    @Override
    public Auth getAuth() {
        return request.getAuth();
    }

    @Override
    public void setContentType(String mediaType) {
        request.setContentType(mediaType);
    }

    @Override
    public void setAccept(String mediaType) {
        request.setAccept(mediaType);
    }

    @Override
    public void addHeader(Header header) {
        request.addHeader(header);
    }

    @Override
    public void setHeader(String header, String value) {
        request.setHeader(header, value);
    }

    @Override
    public void removeHeader(String name) {
        request.removeHeader(name);
    }

    @Override
    public void setAuth(Auth auth) {
        request.setAuth(auth);
    }

    @Override
    public void setTimeout(int timeoutMillis) {
        request.setTimeout(timeoutMillis);
    }

    @Override
    public void setPayload(Object object) {
        request.setPayload(object);
    }

    @Override
    public void setMethod(HttpMethod httpMethod) {
        request.setMethod(httpMethod);
    }

    @Override
    public void proceed() {
        if (processingStack.isEmpty())
            throw new IllegalStateException("Processing steps has ended!");

        final ProcessCallback processor = processingStack.remove(0);
        processor.process();
    }

    @Override
    public void abort(RawResponse response) {
        processingStack.clear();
        dispatcher.evalResponse(request, deferred, resolveType, parametrizedType, response);
    }

    @Override
    public void abort(RequestException error) {
        processingStack.clear();
        deferred.reject(error);
    }

    public void addProcessor(ProcessCallback processor) {
        processingStack.add(processor);
    }
}
