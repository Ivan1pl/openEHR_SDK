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
package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradDefiningCode implements EnumValueSet {
    SCHWER(
            "Schwer",
            "Das Problem oder die Diagnose verhindert die normale Aktivität oder verursacht schwerwiegende gesundheitliche Schäden, falls es nicht behandelt wird.",
            "local",
            "at0049"),

    MASSIG(
            "Mäßig",
            "Das Problem oder die Diagnose beeinträchtigt die normale Aktivität oder verursacht bleibende gesundheitliche Schäden, falls es nicht behandelt wird.",
            "local",
            "at0048"),

    LEICHT(
            "Leicht",
            "Das Problem oder die Diagnose beeinträchtigt die normale Aktivität nicht, bzw. verursacht nicht bleibende gesundheitliche Schäden, falls es nicht behandelt wird.",
            "local",
            "at0047");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    SchweregradDefiningCode(String value, String description, String terminologyId, String code) {
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
