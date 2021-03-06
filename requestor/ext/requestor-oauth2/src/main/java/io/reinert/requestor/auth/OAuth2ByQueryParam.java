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
package io.reinert.requestor.auth;

import io.reinert.requestor.PreparedRequest;
import io.reinert.requestor.oauth2.TokenInfo;

/**
 * OAuth2 authentication through the
 * <a hfref="https://tools.ietf.org/html/rfc6750#section-2.3">URI Query Parameter</a> method.
 *
 * @author Danilo Reinert
 */
public class OAuth2ByQueryParam extends OAuth2Base {

    public OAuth2ByQueryParam(String authUrl, String clientId, String... scopes) {
        super(authUrl, clientId, scopes);
    }

    @Override
    protected void doAuth(PreparedRequest preparedRequest, TokenInfo tokenInfo) {
        preparedRequest.setQueryParam("access_token", tokenInfo.getAccessToken());
    }
}
