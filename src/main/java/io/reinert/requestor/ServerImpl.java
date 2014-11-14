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
package io.reinert.requestor;

import com.google.gwt.core.client.GWT;

/**
 * Default implementation of {@link Server}.
 *
 * @author Danilo Reinert
 */
public class ServerImpl implements Server {

    private final ServerConnection singleton = GWT.create(ServerConnection.class);

    /**
     * Retrieve a singleton instance of {@link ServerConnection} created via DeferredBinding.
     *
     * @return The ServerConnection instance.
     */
    public ServerConnection getConnection() {
        return singleton;
    }
}