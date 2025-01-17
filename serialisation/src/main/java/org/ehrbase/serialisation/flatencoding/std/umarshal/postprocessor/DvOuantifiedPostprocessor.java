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

import com.nedap.archie.rm.datavalues.quantity.DvQuantified;
import java.util.Map;
import java.util.Set;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class DvOuantifiedPostprocessor extends AbstractUnmarshalPostprocessor<DvQuantified> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            DvQuantified rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        setValue(term, "magnitude_status", values, rmObject::setMagnitudeStatus, String.class, consumedPaths);
    }

    /** {@inheritDoc} */
    @Override
    public Class<DvQuantified> getAssociatedClass() {
        return DvQuantified.class;
    }
}
