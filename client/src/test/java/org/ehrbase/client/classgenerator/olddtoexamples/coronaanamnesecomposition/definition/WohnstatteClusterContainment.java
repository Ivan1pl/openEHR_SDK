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

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class WohnstatteClusterContainment extends Containment {
    public SelectAqlField<WohnstatteCluster> WOHNSTATTE_CLUSTER = new AqlFieldImp<WohnstatteCluster>(
            WohnstatteCluster.class, "", "WohnstatteCluster", WohnstatteCluster.class, this);

    public SelectAqlField<Long> ANZAHL_DER_SCHLAFZIMMER_MAGNITUDE = new AqlFieldImp<Long>(
            WohnstatteCluster.class,
            "/items[at0028]/value|magnitude",
            "anzahlDerSchlafzimmerMagnitude",
            Long.class,
            this);

    public ListSelectAqlField<Cluster> ERGANZENDE_DETAILS = new ListAqlFieldImp<Cluster>(
            WohnstatteCluster.class, "/items[at0003]", "erganzendeDetails", Cluster.class, this);

    private WohnstatteClusterContainment() {
        super("openEHR-EHR-CLUSTER.dwelling.v0");
    }

    public static WohnstatteClusterContainment getInstance() {
        return new WohnstatteClusterContainment();
    }
}
