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
package org.ehrbase.validation.terminology.validator;

import org.ehrbase.terminology.openehr.TerminologyInterface;
import org.ehrbase.terminology.openehr.implementation.AttributeCodesetMapping;

public class EventContext extends TerminologyCheck {

    public EventContext() {
        this.RM_CLASS = com.nedap.archie.rm.composition.EventContext.class;
    }

    public static void check(
            TerminologyInterface terminologyInterface,
            AttributeCodesetMapping codesetMapping,
            String context,
            com.nedap.archie.rm.composition.EventContext eventContext,
            String language)
            throws IllegalArgumentException {
        if (eventContext.getSetting() != null)
            validate(
                    terminologyInterface,
                    codesetMapping,
                    "setting",
                    eventContext.getSetting().getDefiningCode(),
                    language);
    }

    public static void check(
            TerminologyInterface terminologyInterface,
            AttributeCodesetMapping codesetMapping,
            String context,
            com.nedap.archie.rm.composition.EventContext eventContext)
            throws IllegalArgumentException {
        check(terminologyInterface, codesetMapping, context, eventContext, "en");
    }
}
