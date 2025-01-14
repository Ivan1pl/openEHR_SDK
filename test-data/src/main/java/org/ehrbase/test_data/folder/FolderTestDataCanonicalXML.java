/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.test_data.folder;

import java.io.InputStream;

public enum FolderTestDataCanonicalXML {
    SIMPLE_EMPTY_FOLDER("A simple empty folder with no sub folders", "simple_empty_folder.xml");

    private final String description;
    private final String filename;

    FolderTestDataCanonicalXML(String description, String filename) {
        this.description = description;
        this.filename = filename;
    }

    public InputStream getStrean() {
        return getClass().getResourceAsStream("/folder/canonical_xml/" + filename);
    }
}
