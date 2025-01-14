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
package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.715778300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class LatestCovid19TestTestRequestDetailsCluster implements LocatableEntity {
    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Test request details/Requester
     * Description: Details of the clinician or organisation requesting the laboratory test result.
     */
    @Path("/items[at0090]")
    private Cluster requester;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Test request details/Distribution list
     * Description: Details of additional clinicians or organisations who require a copy of the test result.
     * Comment: The 'Distribution list' is for information-only, and that the primary recipient of the report is the person intended to act on the information.
     */
    @Path("/items[at0035]")
    private List<Cluster> distributionList;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Test request details/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setRequester(Cluster requester) {
        this.requester = requester;
    }

    public Cluster getRequester() {
        return this.requester;
    }

    public void setDistributionList(List<Cluster> distributionList) {
        this.distributionList = distributionList;
    }

    public List<Cluster> getDistributionList() {
        return this.distributionList;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
