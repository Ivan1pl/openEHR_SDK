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
package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AtiopathogeneseClusterContainment extends Containment {
    public SelectAqlField<AtiopathogeneseCluster> ATIOPATHOGENESE_CLUSTER = new AqlFieldImp<AtiopathogeneseCluster>(
            AtiopathogeneseCluster.class, "", "AtiopathogeneseCluster", AtiopathogeneseCluster.class, this);

    public ListSelectAqlField<AtiopathogeneseAtiologieDerKrankheitElement> ATIOLOGIE_DER_KRANKHEIT =
            new ListAqlFieldImp<AtiopathogeneseAtiologieDerKrankheitElement>(
                    AtiopathogeneseCluster.class,
                    "/items[at0001]",
                    "atiologieDerKrankheit",
                    AtiopathogeneseAtiologieDerKrankheitElement.class,
                    this);

    public ListSelectAqlField<AtiopathogeneseBeschreibungDesEntstehensElement> BESCHREIBUNG_DES_ENTSTEHENS =
            new ListAqlFieldImp<AtiopathogeneseBeschreibungDesEntstehensElement>(
                    AtiopathogeneseCluster.class,
                    "/items[at0017]",
                    "beschreibungDesEntstehens",
                    AtiopathogeneseBeschreibungDesEntstehensElement.class,
                    this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AtiopathogeneseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AtiopathogeneseClusterContainment() {
        super("openEHR-EHR-CLUSTER.etiology.v1");
    }

    public static AtiopathogeneseClusterContainment getInstance() {
        return new AtiopathogeneseClusterContainment();
    }
}
