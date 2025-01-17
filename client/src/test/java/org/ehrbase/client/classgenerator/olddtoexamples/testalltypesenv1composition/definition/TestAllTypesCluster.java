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
package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datavalues.DataValue;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.test_all_types.v1")
public class TestAllTypesCluster {
    @Path("/items[at0001]/items[at0002]/items[at0003]/value")
    private DataValue value;

    @Path("/items[at0001]/items[at0002]/items[at0003]/value|value")
    private Boolean boolean2Value;

    public void setValue(DataValue value) {
        this.value = value;
    }

    public DataValue getValue() {
        return this.value;
    }

    public void setBoolean2Value(Boolean boolean2Value) {
        this.boolean2Value = boolean2Value;
    }

    public Boolean isBoolean2Value() {
        return this.boolean2Value;
    }
}
