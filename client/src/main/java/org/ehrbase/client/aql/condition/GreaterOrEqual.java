/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql.condition;

import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.parameter.Parameter;

public class GreaterOrEqual<T> extends ComparisonOperator<T> {

  protected GreaterOrEqual(SelectAqlField<T> field, T value) {
    super(field, value);
  }

  protected GreaterOrEqual(SelectAqlField<T> field, Parameter<T> parameter) {
    super(field, parameter);
  }

  protected GreaterOrEqual(SelectAqlField<T> field, SelectAqlField<T> compereField) {
    super(field, compereField);
  }

  @Override
  protected String getSymbol() {
    return ">=";
  }
}
