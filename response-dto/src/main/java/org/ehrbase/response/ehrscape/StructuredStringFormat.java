/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.response.ehrscape;

/**
 * Enum containing the available serialization formats compatible with {@link StructuredString}
 */
public enum StructuredStringFormat {
    XML,
    JSON;

    public static StructuredStringFormat fromCompositionFormat(CompositionFormat format) {
        switch (format) {
            case XML:
                return XML;
            case RAW:
            case FLAT:
            case ECISFLAT:
            case EXPANDED:
            case STRUCTURED:
                return JSON;
            default:
                throw new RuntimeException("Unknown Format");
        }
    }
}
