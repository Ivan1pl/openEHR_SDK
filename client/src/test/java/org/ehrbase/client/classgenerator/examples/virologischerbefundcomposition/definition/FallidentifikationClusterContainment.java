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
package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class FallidentifikationClusterContainment extends Containment {
    public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION_CLUSTER = new AqlFieldImp<
            FallidentifikationCluster>(
            FallidentifikationCluster.class, "", "FallidentifikationCluster", FallidentifikationCluster.class, this);

    public SelectAqlField<String> FALL_KENNUNG_VALUE = new AqlFieldImp<String>(
            FallidentifikationCluster.class, "/items[at0001]/value|value", "fallKennungValue", String.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            FallidentifikationCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private FallidentifikationClusterContainment() {
        super("openEHR-EHR-CLUSTER.case_identification.v0");
    }

    public static FallidentifikationClusterContainment getInstance() {
        return new FallidentifikationClusterContainment();
    }
}
