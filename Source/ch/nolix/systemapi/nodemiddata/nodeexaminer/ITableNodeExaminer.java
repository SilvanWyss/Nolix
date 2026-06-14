/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodeexaminer;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface ITableNodeExaminer {
  boolean tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
    IMutableNode<?> tableNode,
    int oneBasedIndex,
    String value,
    IWellOrderContainer<String> entitiesToIgnoreIds);

  boolean tableNodeContainsEntityNodeWithGivenId(IMutableNode<?> tableNode, String id);

  boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
    IMutableNode<?> tableNode,
    int valueIndex,
    String value);

  boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(
    IMutableNode<?> tableNode,
    int index,
    String header);
}
