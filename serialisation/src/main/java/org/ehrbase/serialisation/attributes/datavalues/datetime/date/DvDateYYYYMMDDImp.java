/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.attributes.datavalues.datetime.date;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import java.time.temporal.Temporal;
import org.ehrbase.serialisation.attributes.datavalues.datetime.TemporalAttributes;

/**
 * full date time representation with millisecs
 */
public class DvDateYYYYMMDDImp extends DvDateAttributesImp {

    public DvDateYYYYMMDDImp(DvDate dvDate) {
        super(dvDate);
    }

    @Override
    public Temporal getValueExtended() {
        return dvDate.getValue();
    }

    @Override
    public Integer getSupportedChronoFields() {
        return supportedChronoFields(
                TemporalAttributes.YEAR | TemporalAttributes.MONTH_OF_YEAR | TemporalAttributes.DAY_OF_MONTH);
    }
}
