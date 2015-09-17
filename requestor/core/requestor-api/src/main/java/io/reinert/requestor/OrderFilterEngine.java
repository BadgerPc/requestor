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

import java.util.Iterator;

import io.reinert.requestor.auth.Auth;
import io.reinert.requestor.header.Header;
import io.reinert.requestor.uri.Uri;

/**
 * Responsible for performing request and response filtering.
 *
 * @author Danilo Reinert
 */
class OrderFilterEngine {

    private final FilterManagerImpl filterManager;

    public OrderFilterEngine(FilterManagerImpl filterManager) {
        this.filterManager = filterManager;
    }

    public void filterRequest(RequestOrder request) {
        final Iterator<RequestFilter> it = filterManager.getRequestFilters().iterator();
        if (it.hasNext()) {
            final RequestFilter filter = it.next();
            filter.filter(new RequestFilterContext() {
                @Override
                public String getHeader(String name) {
                    return null;
                }

                @Override
                public void setHeader(String name, String value) {

                }

                @Override
                public void addHeader(Header header) {

                }

                @Override
                public void removeHeader(String name) {

                }

                @Override
                public HttpMethod getMethod() {
                    return null;
                }

                @Override
                public void setMethod(HttpMethod httpMethod) {

                }

                @Override
                public void setAuth(Auth auth) {

                }

                @Override
                public int getTimeout() {
                    return 0;
                }

                @Override
                public void setTimeout(int timeoutMillis) {

                }

                @Override
                public Uri getUri() {
                    return null;
                }

                @Override
                public Object getPayload() {
                    return null;
                }
            });
        }

    }

    public void filterResponse(Request request, ResponseFilterContext response) {
        for (ResponseFilter filter : filterManager.getResponseFilters()) {
            filter.filter(request, response);
        }
    }
}
