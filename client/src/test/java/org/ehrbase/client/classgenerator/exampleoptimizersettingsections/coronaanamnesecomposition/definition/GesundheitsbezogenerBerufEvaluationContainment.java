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
package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class GesundheitsbezogenerBerufEvaluationContainment extends Containment {
    public SelectAqlField<GesundheitsbezogenerBerufEvaluation> GESUNDHEITSBEZOGENER_BERUF_EVALUATION =
            new AqlFieldImp<GesundheitsbezogenerBerufEvaluation>(
                    GesundheitsbezogenerBerufEvaluation.class,
                    "",
                    "GesundheitsbezogenerBerufEvaluation",
                    GesundheitsbezogenerBerufEvaluation.class,
                    this);

    public SelectAqlField<String> BESCHAEFTIGUNGSSTATUS_VALUE = new AqlFieldImp<String>(
            GesundheitsbezogenerBerufEvaluation.class,
            "/data[at0001]/items[at0004]/value|value",
            "beschaeftigungsstatusValue",
            String.class,
            this);

    public ListSelectAqlField<BeschaeftigungCluster> BESCHAEFTIGUNG = new ListAqlFieldImp<BeschaeftigungCluster>(
            GesundheitsbezogenerBerufEvaluation.class,
            "/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]",
            "beschaeftigung",
            BeschaeftigungCluster.class,
            this);

    public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN = new ListAqlFieldImp<Cluster>(
            GesundheitsbezogenerBerufEvaluation.class,
            "/data[at0001]/items[at0005]",
            "zusaetzlicheAngaben",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            GesundheitsbezogenerBerufEvaluation.class,
            "/protocol[at0007]/items[at0008]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            GesundheitsbezogenerBerufEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            GesundheitsbezogenerBerufEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            GesundheitsbezogenerBerufEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private GesundheitsbezogenerBerufEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.occupation_summary.v1");
    }

    public static GesundheitsbezogenerBerufEvaluationContainment getInstance() {
        return new GesundheitsbezogenerBerufEvaluationContainment();
    }
}
