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
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class GestoerterGeschmackssinnObservationContainment extends Containment {
    public SelectAqlField<GestoerterGeschmackssinnObservation> GESTOERTER_GESCHMACKSSINN_OBSERVATION =
            new AqlFieldImp<GestoerterGeschmackssinnObservation>(
                    GestoerterGeschmackssinnObservation.class,
                    "",
                    "GestoerterGeschmackssinnObservation",
                    GestoerterGeschmackssinnObservation.class,
                    this);

    public SelectAqlField<String> BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE = new AqlFieldImp<String>(
            GestoerterGeschmackssinnObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
            "bezeichnungDesSymptomsOderAnzeichensValue",
            String.class,
            this);

    public SelectAqlField<VorhandenDefiningCode> VORHANDEN_DEFINING_CODE = new AqlFieldImp<VorhandenDefiningCode>(
            GestoerterGeschmackssinnObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
            "vorhandenDefiningCode",
            VorhandenDefiningCode.class,
            this);

    public ListSelectAqlField<Cluster> DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN = new ListAqlFieldImp<Cluster>(
            GestoerterGeschmackssinnObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
            "detaillierteAngabenZumSymptomAnzeichen",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            GestoerterGeschmackssinnObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            GestoerterGeschmackssinnObservation.class,
            "/data[at0001]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            GestoerterGeschmackssinnObservation.class,
            "/protocol[at0007]/items[at0021]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            GestoerterGeschmackssinnObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            GestoerterGeschmackssinnObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            GestoerterGeschmackssinnObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private GestoerterGeschmackssinnObservationContainment() {
        super("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0");
    }

    public static GestoerterGeschmackssinnObservationContainment getInstance() {
        return new GestoerterGeschmackssinnObservationContainment();
    }
}
