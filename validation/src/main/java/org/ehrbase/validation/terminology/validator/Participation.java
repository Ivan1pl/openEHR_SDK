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

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.terminology.openehr.TerminologyInterface;
import org.ehrbase.terminology.openehr.implementation.AttributeCodesetMapping;

public class Participation extends TerminologyCheck {

    public Participation() {
        this.RM_CLASS = com.nedap.archie.rm.generic.Participation.class;
    }

    public static void check(
            TerminologyInterface terminologyInterface,
            AttributeCodesetMapping codesetMapping,
            String context,
            com.nedap.archie.rm.generic.Participation participation,
            String language) {
        if (participation.getMode() != null) {
            // validate mode as a DvCodedText
            validate(
                    terminologyInterface,
                    codesetMapping,
                    "mode",
                    new DvCodedText(
                            participation.getMode().getValue(),
                            participation.getMode().getDefiningCode()),
                    language);
        }

        if (participation.getFunction() != null && participation.getFunction() instanceof DvCodedText) {
            validate(terminologyInterface, codesetMapping, "mode", (DvCodedText) participation.getFunction(), language);
        }
    }

    public static void check(
            TerminologyInterface terminologyInterface,
            AttributeCodesetMapping codesetMapping,
            String context,
            com.nedap.archie.rm.generic.Participation participation)
            throws IllegalArgumentException {
        check(terminologyInterface, codesetMapping, context, participation, "en");
    }
}
