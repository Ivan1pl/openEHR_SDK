/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DiagnostischeSicherheitDefiningcode implements EnumValueSet {
    WAHRSCHEINLICH(
            "Wahrscheinlich", "Die Diagnose wurde mit einem hohen Maß an Sicherheit gestellt.", "local", "at0075"),

    VERMUTET("Vermutet", "Die Diagnose wurde mit einem niedrigen Grad an Sicherheit gestellt.", "local", "at0074"),

    BESTATIGT("Bestätigt", "Die Diagnose wurde anhand anerkannter Kriterien bestätigt.", "local", "at0076");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    DiagnostischeSicherheitDefiningcode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
