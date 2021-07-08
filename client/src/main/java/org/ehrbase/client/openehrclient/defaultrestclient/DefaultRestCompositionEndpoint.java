/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

import com.nedap.archie.rm.composition.Composition;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.VersionUid;

public class DefaultRestCompositionEndpoint implements CompositionEndpoint {
  public static final String COMPOSITION_PATH = "/rest/openehr/v1/composition/";
  private final DefaultRestClient defaultRestClient;
  private final UUID ehrId;

  public DefaultRestCompositionEndpoint(DefaultRestClient defaultRestClient, UUID ehrId) {
    this.defaultRestClient = defaultRestClient;
    this.ehrId = ehrId;
  }

  static Optional<VersionUid> extractVersionUid(Object entity) {
    return Arrays.stream(entity.getClass().getDeclaredFields())
        .filter(f -> f.isAnnotationPresent(Id.class))
        .findAny()
        .map(
            idField -> {
              try {
                PropertyDescriptor propertyDescriptor =
                    new PropertyDescriptor(idField.getName(), entity.getClass());
                return (VersionUid) propertyDescriptor.getReadMethod().invoke(entity);
              } catch (IllegalAccessException
                  | InvocationTargetException
                  | IntrospectionException e) {
                throw new ClientException(e.getMessage(), e);
              }
            });
  }

  @Override
  public <T> T mergeCompositionEntity(T entity) {
    Composition composition =
        (Composition)
            new Unflattener(
                    defaultRestClient.getTemplateProvider(),
                    defaultRestClient.getDefaultValuesProvider())
                .unflatten(entity);

    Optional<VersionUid> versionUid = extractVersionUid(entity);

    final VersionUid updatedVersion;
    if (versionUid.isEmpty()) {
      updatedVersion =
          defaultRestClient.httpPost(
              defaultRestClient
                  .getConfig()
                  .getBaseUri()
                  .resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH),
              composition);
    } else {
      updatedVersion =
          defaultRestClient.httpPut(
              defaultRestClient
                  .getConfig()
                  .getBaseUri()
                  .resolve(
                      EHR_PATH + ehrId.toString() + COMPOSITION_PATH + versionUid.get().getUuid()),
              composition,
              versionUid.get());
    }
    Flattener.addVersion(entity, updatedVersion);
    entity =
        (T)
            new Flattener(defaultRestClient.getTemplateProvider())
                .flatten(composition, entity.getClass());
    Flattener.addVersion(entity, updatedVersion);

    return entity;
  }

  @Override
  public <T> Optional<T> find(UUID compositionId, Class<T> clazz) {
    Optional<Composition> composition =
        defaultRestClient.httpGet(
            defaultRestClient
                .getConfig()
                .getBaseUri()
                .resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH + compositionId.toString()),
            Composition.class);

    return composition.map(
        c -> new Flattener(defaultRestClient.getTemplateProvider()).flatten(c, clazz));
  }
}
