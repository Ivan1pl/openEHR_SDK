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

package org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor;

import com.nedap.archie.rm.archetyped.Link;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvEHRURI;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.support.identification.ObjectId;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.DvCodedTextStdConfiguration;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.DvTextStdConfiguration;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.FeederAuditConfig;
import org.ehrbase.serialisation.walker.Context;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class LocatableMarshalPostprocessor implements MarshalPostprocessor<Locatable> {

  private static final FeederAuditConfig FEEDER_AUDIT_CONFIG = new FeederAuditConfig();

  private static final DvCodedTextStdConfiguration DV_CODED_TEXT_STD_CONFIGURATION =
      new DvCodedTextStdConfiguration();
  private static final DvTextStdConfiguration DV_TEXT_STD_CONFIGURATION =
      new DvTextStdConfiguration();

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      Locatable rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context) {

    MarshalPostprocessor.addValue(
        values,
        term + PATH_DIVIDER + "_uid",
        null,
        Optional.of(rmObject).map(Locatable::getUid).map(ObjectId::getValue).orElse(null));

    if (rmObject.getLinks() != null) {
      IntStream.range(0, rmObject.getLinks().size())
          .forEach(
              i -> {
                Link link = rmObject.getLinks().get(i);
                String termLoop = term + PATH_DIVIDER + "_link:" + i;
                MarshalPostprocessor.addValue(
                    values,
                    termLoop,
                    "meaning",
                    Optional.of(link).map(Link::getMeaning).map(DvText::getValue).orElse(null));
                MarshalPostprocessor.addValue(
                    values,
                    termLoop,
                    "type",
                    Optional.of(link).map(Link::getType).map(DvText::getValue).orElse(null));
                MarshalPostprocessor.addValue(
                    values,
                    termLoop,
                    "target",
                    Optional.of(link).map(Link::getTarget).map(DvEHRURI::getValue).orElse(null));
              });

      if (rmObject.getFeederAudit() != null) {
        values.putAll(
            FEEDER_AUDIT_CONFIG.buildChildValues(
                term + "/_feeder_audit", rmObject.getFeederAudit(), null));
      }
    }

    if (!context.getNodeDeque().peek().getName().equals(rmObject.getName().getValue())) {
      if (rmObject.getName() instanceof DvCodedText) {
        values.putAll(
            DV_CODED_TEXT_STD_CONFIGURATION.buildChildValues(
                term + "/_name", (DvCodedText) rmObject.getName(), context));
      } else {
        values.putAll(
            DV_TEXT_STD_CONFIGURATION.buildChildValues(
                term + "/_name", rmObject.getName(), context));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Locatable> getAssociatedClass() {
    return Locatable.class;
  }
}
