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
package org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary;

import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.ParserSettings;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.BeanListProcessor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;

public class NumericExpressionSettings extends ParserSettings {

    private final BeanListProcessor<NumericExpression> numericExpressionRow =
            new BeanListProcessor<>(NumericExpression.class);

    public CsvParserSettings settings() {
        super.settings();
        settings.setProcessor(numericExpressionRow);
        return settings;
    }

    public BeanListProcessor<NumericExpression> getNumericExpressionRow() {
        return numericExpressionRow;
    }
}
