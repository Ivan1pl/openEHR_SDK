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

import com.nedap.archie.rm.datastructures.IntervalEvent;
import java.util.Map;
import java.util.Set;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class IntervalEventPostprocessor extends AbstractUnmarshalPostprocessor<IntervalEvent> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            IntervalEvent rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        setValue(term, "sample_count", values, rmObject::setSampleCount, Long.class, consumedPaths);
    }

    /** {@inheritDoc} */
    @Override
    public Class<IntervalEvent> getAssociatedClass() {
        return IntervalEvent.class;
    }
}
