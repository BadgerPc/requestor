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

import java.util.ListIterator;

public class RequestProcessorStackerImpl implements RequestProcessorStacker {

    private final FilterManagerImpl filterManager;
    private final InterceptorManagerImpl interceptorManager;

    public RequestProcessorStackerImpl(FilterManagerImpl filterManager, InterceptorManagerImpl interceptorManager) {
        this.filterManager = filterManager;
        this.interceptorManager = interceptorManager;
    }

    @Override
    public void stackRequestProcessors(StackableProcessingRequest requestOrder) {
        final ListIterator<RequestInterceptor> interceptorIterator = interceptorManager
                .getRequestInterceptors().listIterator(interceptorManager.getRequestInterceptors().size());

        // Avoids creating one new context for each interceptor
        final RequestInterceptorContextImpl interceptorContext = new RequestInterceptorContextImpl(requestOrder);

        while (interceptorIterator.hasPrevious()) {
            final RequestInterceptor requestInterceptor = interceptorIterator.previous();
            requestOrder.addProcessor(new ProcessCallback() {
                @Override
                public void process() {
                    requestInterceptor.intercept(interceptorContext);
                }
            });
        }



        final ListIterator<RequestFilter> filterIterator = filterManager
                .getRequestFilters().listIterator(filterManager.getRequestFilters().size());

        // Avoids creating one new context for each filter
        final RequestFilterContextImpl filterContext = new RequestFilterContextImpl(requestOrder);

        while (filterIterator.hasPrevious()) {
            final RequestFilter requestFilter = filterIterator.previous();
            requestOrder.addProcessor(new ProcessCallback() {
                @Override
                public void process() {
                    requestFilter.filter(filterContext);
                }
            });
        }
    }
}
