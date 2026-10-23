/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface ITableNodeSearcher {
  /**
   * @param tableNode
   * @param columnName
   * @return the one-based index of the column with the given columnName in the
   *         given tableNode
   * @throws RuntimeException if the given tableNode does not contain a column
   *                          with the given columnName
   */
  int getOneBasedIndexOfColumnInTableNodeByColumnName(IMutableNode<?> tableNode, String columnName);

  /**
   * @param tableNode
   * @param columnId
   * @return the column node with the given columnId from the given tableNode
   * @throws RuntimeException if the given tableNode does not contain a column
   *                          node with the given columnId
   */
  IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnId(IMutableNode<?> tableNode, String columnId);

  /**
   * @param tableNode
   * @param columnName
   * @return the column node with the given columnName from the given tableNode
   * @throws RuntimeException if the given tableNode does not contain a column
   *                          node with the given columnName
   */
  IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(IMutableNode<?> tableNode, String columnName);

  /**
   * @param tableNode
   * @return the column nodes from the given tableNode
   */
  ExtendedIterable<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the entity nodes from the given tableNode
   */
  ExtendedIterable<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the id node from the given tableNode
   */
  IMutableNode<?> getStoredIdNodeFromTableNode(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the name node from the given tableNode
   */
  IMutableNode<?> getStoredNameNodeFromTableNode(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the table id from the given tableNode
   */
  String getTableIdFromTableNode(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the table name from the given tableNode
   */
  String getTableNameFromTableNode(IMutableNode<?> tableNode);
}
