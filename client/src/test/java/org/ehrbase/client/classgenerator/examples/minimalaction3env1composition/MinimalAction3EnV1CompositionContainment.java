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
package org.ehrbase.client.classgenerator.examples.minimalaction3env1composition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.minimalaction3env1composition.definition.MinimalAction;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class MinimalAction3EnV1CompositionContainment extends Containment {
    public SelectAqlField<MinimalAction3EnV1Composition> MINIMAL_ACTION3_EN_V1_COMPOSITION =
            new AqlFieldImp<MinimalAction3EnV1Composition>(
                    MinimalAction3EnV1Composition.class,
                    "",
                    "MinimalAction3EnV1Composition",
                    MinimalAction3EnV1Composition.class,
                    this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            MinimalAction3EnV1Composition.class,
            "/category|defining_code",
            "categoryDefiningCode",
            Category.class,
            this);

    public ListSelectAqlField<MinimalAction> MINIMAL = new ListAqlFieldImp<MinimalAction>(
            MinimalAction3EnV1Composition.class,
            "/content[openEHR-EHR-ACTION.minimal.v1]",
            "minimal",
            MinimalAction.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(
            MinimalAction3EnV1Composition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            MinimalAction3EnV1Composition.class, "/language", "language", Language.class, this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            MinimalAction3EnV1Composition.class,
            "/context/start_time|value",
            "startTimeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            MinimalAction3EnV1Composition.class,
            "/context/participations",
            "participations",
            Participation.class,
            this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            MinimalAction3EnV1Composition.class,
            "/context/end_time|value",
            "endTimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(
            MinimalAction3EnV1Composition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            MinimalAction3EnV1Composition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            MinimalAction3EnV1Composition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            MinimalAction3EnV1Composition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(
            MinimalAction3EnV1Composition.class, "/territory", "territory", Territory.class, this);

    private MinimalAction3EnV1CompositionContainment() {
        super("openEHR-EHR-COMPOSITION.minimal.v1");
    }

    public static MinimalAction3EnV1CompositionContainment getInstance() {
        return new MinimalAction3EnV1CompositionContainment();
    }
}
