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
package org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.ehrbase.client.Integration;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryQuery;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class ArbitraryQueryIT extends CanonicalCompoAllTypeQueryIT {

    protected ArbitraryQuery arbitraryQuery;

    @Before
    public void setUp() throws IOException {
        super.setUp(CompositionTestDataCanonicalJson.ALL_TYPES_SYSTEMATIC_TESTS);
        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
    }

    @Test
    public void testArbitraryBoolean() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/arbitrary_boolean_where_tests.csv";

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }

    @Test
    public void testArbitraryString() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/arbitrary_tests.csv";

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }
}
