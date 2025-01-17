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

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v1")
public class StatusCluster {
    @Path("/items[at0004]/value|defining_code")
    private DiagnosestatusDefiningcode diagnosestatusDefiningcode;

    public void setDiagnosestatusDefiningcode(DiagnosestatusDefiningcode diagnosestatusDefiningcode) {
        this.diagnosestatusDefiningcode = diagnosestatusDefiningcode;
    }

    public DiagnosestatusDefiningcode getDiagnosestatusDefiningcode() {
        return this.diagnosestatusDefiningcode;
    }
}
