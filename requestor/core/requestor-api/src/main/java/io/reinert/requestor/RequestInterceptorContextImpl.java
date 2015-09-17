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

import io.reinert.requestor.uri.Uri;

public class RequestInterceptorContextImpl extends AbstractRequestOrder implements RequestInterceptorContext {

    public RequestInterceptorContextImpl(ProcessingRequest request) {
        super(request);
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
    public int getTimeout() {
        return request.getTimeout();
    }

    @Override
    public Uri getUri() {
        return request.getUri();
    }

    @Override
    public Payload getPayload() {
        return (Payload) request.getPayload();
    }

    @Override
    public void setPayload(Payload payload) {
        request.setPayload(payload);
    }
}
