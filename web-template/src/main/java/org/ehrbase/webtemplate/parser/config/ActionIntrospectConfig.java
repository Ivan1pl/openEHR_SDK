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
package org.ehrbase.webtemplate.parser.config;

import com.nedap.archie.rm.composition.Action;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActionIntrospectConfig extends CareEntryIntrospectConfig implements RmIntrospectConfig {

    private static final Set<String> FIELDS = Stream.of("time").collect(Collectors.toSet());

    @Override
    public Class getAssociatedClass() {
        return Action.class;
    }

    @Override
    public Set<String> getNonTemplateFields() {
        FIELDS.addAll(super.getNonTemplateFields());
        return FIELDS;
    }
}
