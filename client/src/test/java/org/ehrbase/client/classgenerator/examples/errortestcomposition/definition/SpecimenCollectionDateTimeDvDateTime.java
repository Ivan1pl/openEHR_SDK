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
package org.ehrbase.client.classgenerator.examples.errortestcomposition.definition;

import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2022-03-02T14:11:00.844601200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_DATE_TIME")
public class SpecimenCollectionDateTimeDvDateTime implements RMEntity, SpecimenCollectionDateTimeChoice {
    /**
     * Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/Collection
     * date/time Description: The date and time that collection has been ordered to take place or has
     * taken place.
     */
    @Path("|value")
    private TemporalAccessor collectionDateTimeValue;

    public void setCollectionDateTimeValue(TemporalAccessor collectionDateTimeValue) {
        this.collectionDateTimeValue = collectionDateTimeValue;
    }

    public TemporalAccessor getCollectionDateTimeValue() {
        return this.collectionDateTimeValue;
    }
}
