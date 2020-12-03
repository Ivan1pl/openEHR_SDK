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

package org.ehrbase.serialisation.walker;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Walker<T> {

  public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

  public void walk(Composition composition, T object, WebTemplate webTemplate) {

    walk(composition, object, webTemplate.getTree());
  }

  public void walk(RMObject composition, T object, WebTemplateNode root) {

    Context<T> context = new Context<>();

    context.getNodeDeque().push(root);
    context.getObjectDeque().push(object);
    context.getRmObjectDeque().push(composition);

    handle(context);
  }

  private void handle(Context<T> context) {

    preHandle(context);
    WebTemplateNode currentNode = context.getNodeDeque().peek();
    if (visitChildren(currentNode)) {

      Map<String, List<WebTemplateNode>> choices = currentNode.getChoicesInChildren();
      List<WebTemplateNode> children = new ArrayList<>(currentNode.getChildren());
      if (children.stream()
          .filter(n -> n.getRmType().equals("DV_CODED_TEXT"))
          .map(WebTemplateNode::getInputs)
          .flatMap(List::stream)
          .map(WebTemplateInput::getSuffix)
          .anyMatch("other"::equals)) {
        WebTemplateNode codeNode =
            children.stream().filter(n -> n.getRmType().equals("DV_CODED_TEXT")).findAny().get();
        WebTemplateNode textNode = new WebTemplateNode(codeNode);
        textNode.setRmType("DV_TEXT");
        choices.put(textNode.getAqlPath(), List.of(codeNode, textNode));
        children.add(textNode);
      }

      if (children.stream().anyMatch(n -> n.getRmType().equals("EVENT"))) {
        WebTemplateNode event =
            children.stream().filter(n -> n.getRmType().equals("EVENT")).findAny().get();
        WebTemplateNode pointEvent = new WebTemplateNode(event);
        WebTemplateNode intervalEvent = new WebTemplateNode(event);
        pointEvent.setRmType("POINT_EVENT");
        intervalEvent.setRmType("INTERVAL_EVENT");
        WebTemplateNode width = new WebTemplateNode();
        width.setId("width");
        width.setRmType("DV_DURATION");
        width.setMax(1);
        width.setAqlPath(event.getAqlPath() + "/width");
        intervalEvent.getChildren().add(width);
        WebTemplateNode math = new WebTemplateNode();
        math.setId("math_function");
        math.setRmType("DV_CODED_TEXT");
        math.setMax(1);
        math.setAqlPath(event.getAqlPath() + "/math_function");
        intervalEvent.getChildren().add(math);
        choices.put(intervalEvent.getAqlPath(), List.of(intervalEvent, pointEvent));
        children.add(intervalEvent);
        children.add(pointEvent);
        children.remove(event);
      }

      Collection<List<WebTemplateNode>> childChoices = children.stream()
              .collect(Collectors.groupingBy(WebTemplateNode::getAqlPath))
              .entrySet()
              .stream()
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
              .values();

      for (List<WebTemplateNode> choces : childChoices) {

        if (choces.stream().noneMatch(WebTemplateNode::isMulti)) {

          for (WebTemplateNode childNode : choces) {
            ImmutablePair<T, RMObject> pair =
                extractPair(context, currentNode, choices, childNode, null);
            T childObject = pair.getLeft();
            Object child = pair.getRight();

            if (child != null && childObject != null) {
              context.getNodeDeque().push(childNode);
              context.getObjectDeque().push(childObject);
              context.getRmObjectDeque().push((RMObject) child);
              handle(context);
            }
          }
        } else {
          int size = calculateSize(context, choces.get(0));


          List<Triple<T, RMObject,WebTemplateNode>> pairs = new ArrayList<>();
          for (int i = 0; i < size; i++) {
            for (WebTemplateNode childNode : choces) {
              ImmutablePair<T, RMObject> pair =
                  extractPair(context, currentNode, choices, childNode, i);
              if (pair.getLeft() != null && pair.getRight() != null) {
                pairs.add(new ImmutableTriple<>(pair.getLeft(),pair.getRight(),childNode));
              }
            }
          }

          for (int i = 0; i < size; i++) {
            RMObject currentChild = null;
            T childObject = null;
            childObject = pairs.get(i).getLeft();
            currentChild = pairs.get(i).getMiddle();
            WebTemplateNode childNode = pairs.get(i).getRight();
            if (currentChild != null && childObject != null) {
              context.getNodeDeque().push(childNode);
              context.getObjectDeque().push(childObject);
              context.getRmObjectDeque().push(currentChild);
              context.getCountMap().put(childNode, i);
              handle(context);
            }
          }
        }
      }
    }
    postHandle(context);
    context.getRmObjectDeque().remove();
    context.getNodeDeque().remove();
    context.getObjectDeque().remove();
  }

  protected abstract ImmutablePair<T, RMObject> extractPair(
      Context<T> context,
      WebTemplateNode currentNode,
      Map<String, List<WebTemplateNode>> choices,
      WebTemplateNode childNode,
      Integer i);

  protected abstract Object extractRMChild(
      RMObject currentRM,
      WebTemplateNode currentNode,
      WebTemplateNode childNode,
      boolean isChoice,
      Integer count);

  protected boolean visitChildren(WebTemplateNode node) {
    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    return typeInfo != null
        && (Locatable.class.isAssignableFrom(typeInfo.getJavaClass())
            || EventContext.class.isAssignableFrom(typeInfo.getJavaClass())
            || DvInterval.class.isAssignableFrom(typeInfo.getJavaClass())
    || IsmTransition.class.isAssignableFrom(typeInfo.getJavaClass()));
  }

  protected abstract T extract(
      Context<T> context, WebTemplateNode child, boolean isChoice, Integer i);

  protected abstract void preHandle(Context<T> context);

  protected abstract void postHandle(Context<T> context);

  protected abstract int calculateSize(Context<T> context, WebTemplateNode childNode);

  protected RMObject deepClone(RMObject rmObject) {
    if (rmObject == null) {
      return null;
    }
    CanonicalJson canonicalXML = new CanonicalJson();
    return canonicalXML.unmarshal(canonicalXML.marshal(rmObject), rmObject.getClass());
  }

  protected String buildNamePath(Context<T> context) {
    StringBuilder sb = new StringBuilder();
    for (Iterator<WebTemplateNode> iterator = context.getNodeDeque().descendingIterator();
        iterator.hasNext(); ) {
      WebTemplateNode node = iterator.next();
      sb.append(node.getId());
      if (node.getMax() != 1 && context.getCountMap().containsKey(node)) {
        sb.append(":").append(context.getCountMap().get(node));
      }
      if (iterator.hasNext()) {
        sb.append("/");
      }
    }
    return sb.toString();
  }

  protected class ItemExtractor {
    private RMObject currentRM;
    private WebTemplateNode currentNode;
    private WebTemplateNode childNode;
    private boolean isChoice;
    private String relativeAql;
    private Object child;
    private String parentAql;
    private Object parent;

    public ItemExtractor(
        RMObject currentRM,
        WebTemplateNode currentNode,
        WebTemplateNode childNode,
        boolean isChoice) {
      this.currentRM = currentRM;
      this.currentNode = currentNode;
      this.childNode = childNode;
      this.isChoice = isChoice;
    }

    public FlatPath getRelativeAql() {
      return new FlatPath(relativeAql);
    }

    public Object getChild() {
      return child;
    }

    public ItemExtractor invoke() {
      relativeAql =
          StringUtils.removeEnd(
              StringUtils.removeStart(childNode.getAqlPath(), currentNode.getAqlPath()), "/");
      FlatPath childPath = new FlatPath(relativeAql);
      parentAql =
          StringUtils.removeEnd(
              childPath.format(false),
              childPath.format(false).substring(childPath.format(false).lastIndexOf("/")));
      if (StringUtils.isBlank(parentAql)) {
        parentAql = "/";
      }

      if (currentRM instanceof Pathable) {
        try {
          child = ((Pathable) currentRM).itemsAtPath(childPath.format(false));
          if (child == null || ((List) child).isEmpty()) {
            child = ((Pathable) currentRM).itemAtPath(childPath.format(false));
          }
        } catch (RuntimeException e) {
          child = null;
        }
        parent = ((Pathable) currentRM).itemAtPath(parentAql);
      } else if (currentRM instanceof DvInterval) {
        if (relativeAql.contains("upper_included")) {
          child = new RmBoolean(((DvInterval<?>) currentRM).isUpperIncluded());
        } else if (relativeAql.contains("lower_included")) {
          child = new RmBoolean(((DvInterval<?>) currentRM).isLowerIncluded());
        } else if (relativeAql.contains("lower")) {
          child = ((DvInterval<?>) currentRM).getLower();
        } else if (relativeAql.contains("upper")) {
          child = ((DvInterval<?>) currentRM).getUpper();
        }
        parent = currentRM;
      } else {
        throw new SdkException(
            String.format("Can not extract from class %s", currentRM.getClass().getSimpleName()));
      }

      if (StringUtils.isNotBlank(childPath.findOtherPredicate("name/value"))
          && child instanceof List
          && Locatable.class.isAssignableFrom(
              ARCHIE_RM_INFO_LOOKUP.getClass(childNode.getRmType()))) {
        child =
            ((List) child)
                .stream()
                    .filter(
                        c ->
                            childPath
                                .findOtherPredicate("name/value")
                                .equals(((Locatable) c).getNameAsString()))
                    .collect(Collectors.toList());
        // if name not found return null
        if (((List<?>) child).isEmpty()) {
          child = null;
        }
      }
      if (isChoice && child instanceof List) {
        child =
            ((List) child)
                .stream()
                    .filter(
                        c ->
                            ARCHIE_RM_INFO_LOOKUP
                                .getTypeInfo(c.getClass())
                                .getRmName()
                                .equals(childNode.getRmType()))
                    .collect(Collectors.toList());
        // if rmType not found return null
        if (((List<?>) child).isEmpty()) {
          child = null;
        }
      }

      if (childNode.getMax() == 1 && child instanceof List) {

        if (((List<?>) child).isEmpty()) {
          child = null;
        } else {
          child = ((List) child).get(0);
        }
      }

      if (child instanceof Element && !childNode.getRmType().equals("ELEMENT")) {
        child = ((Element) child).getValue();
      }
      return this;
    }

    public FlatPath getParentAql() {
      return new FlatPath(parentAql);
    }

    public Object getParent() {
      return parent;
    }
  }
}
