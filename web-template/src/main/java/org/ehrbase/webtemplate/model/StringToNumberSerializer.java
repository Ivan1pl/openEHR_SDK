/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.webtemplate.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.apache.commons.lang3.math.NumberUtils;

public class StringToNumberSerializer extends StdSerializer<String> {

    public StringToNumberSerializer() {
        this(null);
    }

    public StringToNumberSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (NumberUtils.isParsable(value)) {
            Number number = NumberUtils.createNumber(value);
            if (number instanceof Double) {
                gen.writeNumber((Double) number);
            } else if (number instanceof Integer) {
                gen.writeNumber((Integer) number);
            } else if (number instanceof Float) {
                gen.writeNumber((Float) number);
            } else if (number instanceof Long) {
                gen.writeNumber((Long) number);
            } else {
                gen.writeString(value);
            }

        } else {
            gen.writeString(value);
        }
    }
}
