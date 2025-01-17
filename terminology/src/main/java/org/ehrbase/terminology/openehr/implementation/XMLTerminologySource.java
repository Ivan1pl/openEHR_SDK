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
package org.ehrbase.terminology.openehr.implementation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.ehrbase.terminology.openehr.TerminologyResourceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class provides access to terminology content in XML format
 *
 * @author rong.chen
 */
public class XMLTerminologySource implements TerminologySource {

    /**
     * Gets an terminology source loaded with specified xml content
     */
    public static XMLTerminologySource getInstance(String xmlfilename) throws TerminologyResourceException {
        return new XMLTerminologySource(xmlfilename);
    }

    public List<CodeSet> getCodeSets() {
        return codeSetList;
    }

    public List<Group> getConceptGroups() {
        return groupList;
    }

    /*
     * Constructs an instance loaded with terminology content
     */
    private XMLTerminologySource(String filename) throws TerminologyResourceException {
        codeSetList = new ArrayList<>();
        groupList = new ArrayList<>();
        loadTerminologyFromXML(filename);
    }

    private void loadTerminologyFromXML(String filename) throws TerminologyResourceException {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filename)) {

            if (resourceAsStream == null) {
                throw new TerminologyResourceException("Could not access filename:" + filename);
            }

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            final DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            final Document document = documentBuilder.parse(resourceAsStream);
            Element root = document.getDocumentElement();
            NodeList codesets = root.getElementsByTagName("codeset");
            codeSetList.clear();
            groupList.clear();

            for (int idx = 0; idx < codesets.getLength(); idx++) {
                Element element = (Element) codesets.item(idx);
                codeSetList.add(loadCodeSet(element));
            }

            NodeList groups = root.getElementsByTagName("group");
            for (int idx = 0; idx < groups.getLength(); idx++) {
                Element element = (Element) groups.item(idx);
                groupList.add(loadGroup(element));
            }
        } catch (Exception e) {
            throw new TerminologyResourceException(e.getMessage());
        }
    }

    /*
     * Loads a code set from XML element
     */
    private CodeSet loadCodeSet(Element element) {
        CodeSet codeset = new CodeSet();
        codeset.openehrId = element.getAttribute("openehr_id");
        codeset.issuer = element.getAttribute("issuer");
        codeset.externalId = element.getAttribute("external_id");
        NodeList children = element.getElementsByTagName("code");
        for (int idx = 0; idx < children.getLength(); idx++) {
            Element code = (Element) children.item(idx);
            codeset.addCode(code.getAttribute("value"), code.getAttribute("description"));
        }
        return codeset;
    }

    /*
     * Loads a concept group from XML element
     */
    private Group loadGroup(Element element) {
        Group group = new Group();
        group.name = element.getAttribute("name");

        NodeList children = element.getElementsByTagName("concept");
        for (int idx = 0; idx < children.getLength(); idx++) {
            Concept concept = new Concept();
            Element e = (Element) children.item(idx);
            concept.id = (e.getAttribute("id"));
            concept.rubric = (e.getAttribute("rubric"));
            group.addConcept(concept);
        }
        return group;
    }

    private List<Group> groupList;
    private List<CodeSet> codeSetList;
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is XMLTerminologySource.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */
