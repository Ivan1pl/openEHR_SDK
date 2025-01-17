/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class ElementPostprocessor extends AbstractUnmarshalPostprocessor<Element> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            Element rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        Map<FlatPathDto, String> reasonValues = FlatHelper.filter(values, term + "/_null_reason", false);

        if (!reasonValues.isEmpty()) {

            if (FlatHelper.isExactlyDvCodedText(reasonValues, term + "_null_reason")) {
                rmObject.setNullReason(new DvCodedText());
            } else {
                rmObject.setNullReason(new DvText());
            }
            handleRmAttribute(term, rmObject.getNullReason(), reasonValues, consumedPaths, context, "null_reason");
        }

        setValue(
                term + PATH_DIVIDER + "_null_flavour",
                "value",
                values,
                s -> {
                    if (StringUtils.isNotBlank(s)) {
                        rmObject.setNullFlavour(FlatHelper.findEnumValueOrThrow(s, NullFlavour.class)
                                .toCodedText());
                    }
                },
                String.class,
                consumedPaths);

        setValue(
                term + PATH_DIVIDER + "_null_flavour",
                "code",
                values,
                s -> {
                    if (StringUtils.isNotBlank(s)) {
                        rmObject.setNullFlavour(FlatHelper.findEnumValueOrThrow(s, NullFlavour.class)
                                .toCodedText());
                    }
                },
                String.class,
                consumedPaths);

        FlatHelper.consumeAllMatching(term + PATH_DIVIDER + "_null_flavour|terminology", values, consumedPaths, false);
        if (rmObject.getNullFlavour() != null) {
            rmObject.setValue(null);
            FlatHelper.consumeAllMatching(term, values, consumedPaths, false);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<Element> getAssociatedClass() {
        return Element.class;
    }
}
