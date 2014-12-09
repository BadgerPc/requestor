/*
 * Copyright 2014 Danilo Reinert
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
package io.reinert.requestor.test.mock;

import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

import io.reinert.requestor.HttpConnection;
import io.reinert.requestor.RequestProgress;

/**
 * A mock of {@link io.reinert.requestor.HttpConnection}.
 * <p/>
 * You should add expected {@link Response}s to the underlying server stub with #responseFor in order to mock responses
 * from server.
 *
 * @author Danilo Reinert
 */
public class ServerConnectionMock implements HttpConnection {

    private static final RequestProgress REQUEST_PROGRESS = new RequestProgress() {
        @Override
        public boolean isLengthComputable() {
            return false;
        }

        @Override
        public double loaded() {
            return 0;
        }

        @Override
        public double total() {
            return 0;
        }
    };
    private static RequestCallback requestCallback;
    private static String uri;

    public ServerConnectionMock() {
//        ServerStub.setRequestData(url, new RequestMock(httpMethod, url, data, headers));
//        uri = url;
//        requestCallback = (RequestCallbackWithProgress) callback;
//        return null;
    }

    static void triggerPendingRequest() {
//        requestCallback.onProgress(REQUEST_PROGRESS);
        if (ServerStub.isReturnSuccess()) {
            requestCallback.onResponseReceived(null, ServerStub.getResponseFor(uri));
        } else {
            requestCallback.onError(null, new RequestException("This is a mock exception."));
        }

        uri = null;
        requestCallback = null;
    }

    @Override
    public void cancel() {
        // Do nothing
    }

    @Override
    public boolean isPending() {
        return false;
    }

}
