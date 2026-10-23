/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodeexaminer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface ITableNodeExaminer {
  /**
   * @param tableNode
   * @param valueIndex
   * @param value
   * @return true if the given tableNode contains an entity node whose field at
   *         the given valueIndex contains the given value, false otherwise
   */
  boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
    IMutableNode<?> tableNode,
    int valueIndex,
    String value);

  /**
   * @param tableNode
   * @param index
   * @param header
   * @return true if the given tableNode contains an entity node whose field at
   *         the given index has the given header, false otherwise
   */
  boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(
    IMutableNode<?> tableNode,
    int index,
    String header);

  /**
   * @param tableNode
   * @param oneBasedIndex
   * @param value
   * @param entitiesToIgnoreIds
   * @return true if the given tableNode contains an entity node, that does not
   *         have one of the given entitiesToIgnoreIds, whose field at the given
   *         oneBasedIndex has the given value, false otherwise
   */
  boolean tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
    IMutableNode<?> tableNode,
    int oneBasedIndex,
    String value,
    ExtendedIterable<String> entitiesToIgnoreIds);

  /**
   * @param tableNode
   * @param id
   * @return true if the given tableNode contains an entity node with the given
   *         id, false otherwise
   */
  boolean tableNodeContainsEntityNodeWithGivenId(IMutableNode<?> tableNode, String id);
}
