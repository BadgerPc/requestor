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

/**
 * The type of response expected from the XMLHttpRequest.
 */
public enum ResponseType {

    DEFAULT(""),
    ARRAY_BUFFER("arraybuffer"),
    BLOB("blob"),
    DOCUMENT("document"),
    JSON("json"),
    TEXT("text");

    private final String value;

    private ResponseType(String value) {
        this.value = value;
    }

    public static ResponseType of(String responseTypeString) {
        final String value = responseTypeString.toLowerCase();
        if (value.equals("arraybuffer")) {
            return ARRAY_BUFFER;
        } else if (value.equals("blob")) {
            return BLOB;
        } else if (value.equals("document")) {
            return DOCUMENT;
        } else if (value.equals("json")) {
            return JSON;
        } else if (value.equals("text")) {
            return TEXT;
        } else {
            return DEFAULT;
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
