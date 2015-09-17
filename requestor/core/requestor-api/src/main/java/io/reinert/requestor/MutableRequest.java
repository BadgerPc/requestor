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

import io.reinert.requestor.auth.Auth;
import io.reinert.requestor.header.Header;

/**
 * A fluent request builder.
 *
 * @author Danilo Reinert
 */
public interface MutableRequest extends Request {

    /**
     * Set the content type of this request.
     *
     * @param mediaType The content type of this request
     */
    void setContentType(String mediaType);

    /**
     * Set the content type accepted for the response.
     *
     * @param mediaType The content type accepted for the response
     */
    void setAccept(String mediaType);

    /**
     * Adds a request header.
     *
     * If a header with the specified name has already been set
     * then the new value overwrites the current value.
     *
     * @param header The header instance
     */
    void addHeader(Header header);

    /**
     * Sets a request header with the given name and value.
     *
     * If a header with the specified name has already been set
     * then the new value overwrites the current value.
     *
     * If a null value is given, then any existing header with
     * the given name is removed.
     *
     * @param header The name of the header
     * @param value  The value of the header
     */
    void setHeader(String header, String value);

    /**
     * Removes the header with the given name if it exists in the current request.
     *
     * @param name The name of the header to remove
     */
    void removeHeader(String name);

    /**
     * Sets the necessary information for authenticating the request against the server.
     *
     * @param auth The authentication procedure
     */
    void setAuth(Auth auth);

    /**
     * Sets the number of milliseconds to wait for a request to complete.
     *
     * Should the request timeout, registered RejectedCallbacks will be called in the returning Promise.
     * The callback method will receive an instance of the {@link RequestTimeoutException} class as its
     * {@link Throwable} argument.
     * <p/>
     *
     * Negative aren't allowed according to XMLHttpRequest specification.
     * So if a value less than zero is passed, it is ignored.
     *
     * @param timeoutMillis Number of milliseconds to wait before canceling the
     *                      request, a value of zero disables timeouts
     */
    void setTimeout(int timeoutMillis);

    /**
     * Input a serialized payload to be sent in the HTTP Request payload.
     *
     * @param payload The payload of the request
     */
    void setPayload(Payload payload);

    /**
     * Sets the HTTP method of the request.
     *
     * @param httpMethod The HTTP method of the request
     */
    void setMethod(HttpMethod httpMethod);

}
