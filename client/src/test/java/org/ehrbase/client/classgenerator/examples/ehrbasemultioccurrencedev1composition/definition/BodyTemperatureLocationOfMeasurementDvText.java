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
package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:11.012496400+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_TEXT")
public class BodyTemperatureLocationOfMeasurementDvText
        implements RMEntity, BodyTemperatureLocationOfMeasurementChoice {
    /**
     * Path: Encounter/Body temperature/value/value
     */
    @Path("|value")
    private String locationOfMeasurementValue;

    public void setLocationOfMeasurementValue(String locationOfMeasurementValue) {
        this.locationOfMeasurementValue = locationOfMeasurementValue;
    }

    public String getLocationOfMeasurementValue() {
        return this.locationOfMeasurementValue;
    }
}
