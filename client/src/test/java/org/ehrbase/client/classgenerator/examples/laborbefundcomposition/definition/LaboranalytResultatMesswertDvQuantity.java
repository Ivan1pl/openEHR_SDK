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
package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-06-10T14:23:37.606792+07:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0")
@OptionFor("DV_QUANTITY")
public class LaboranalytResultatMesswertDvQuantity implements RMEntity, LaboranalytResultatMesswertChoice {
    /**
     * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Messwert/Messwert
     * Description: (Mess-)Wert des Analyt-Resultats.
     */
    @Path("|magnitude")
    private Double messwertMagnitude;

    /**
     * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Messwert/Messwert
     * Description: (Mess-)Wert des Analyt-Resultats.
     */
    @Path("|units")
    private String messwertUnits;

    public void setMesswertMagnitude(Double messwertMagnitude) {
        this.messwertMagnitude = messwertMagnitude;
    }

    public Double getMesswertMagnitude() {
        return this.messwertMagnitude;
    }

    public void setMesswertUnits(String messwertUnits) {
        this.messwertUnits = messwertUnits;
    }

    public String getMesswertUnits() {
        return this.messwertUnits;
    }
}
