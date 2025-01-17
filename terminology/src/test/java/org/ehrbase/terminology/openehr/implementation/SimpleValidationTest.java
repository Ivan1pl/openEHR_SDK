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
package org.ehrbase.terminology.openehr.implementation;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.terminology.openehr.TerminologyInterface;
import org.junit.Test;

public class SimpleValidationTest {

    @Test
    public void testSimpleValidation() throws Exception {
        TerminologyInterface simpleTerminologyInterface = new SimpleTerminologyInterface("en");
        AttributeCodesetMapping codesetMapping = AttributeCodesetMapping.getInstance();

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("openehr"), "433");
        DvCodedText category = new DvCodedText("event", codePhrase);

        // get the actual attribute
        String attribute = codesetMapping.actualAttributeId("openehr", "category", "en");
        ContainerType containerType = codesetMapping.containerType("openehr", "category");
        switch (containerType) {
            case GROUP: // a code string defined within a group of a codeset
                boolean valid =
                        simpleTerminologyInterface.terminology("openehr").hasCodeForGroupId(attribute, codePhrase);
                // check if the supplied value matches codephrase
                String rubric = simpleTerminologyInterface
                        .terminology("openehr")
                        .rubricForCode(codePhrase.getCodeString(), "en");
                valid = rubric.equals("event");
                break;

            case CODESET: // a codestring defined in a codeset
                valid = simpleTerminologyInterface.codeSet(attribute).hasCode(codePhrase);
                break;

            default:
                throw new IllegalArgumentException("undefined container type");
        }
    }
}
