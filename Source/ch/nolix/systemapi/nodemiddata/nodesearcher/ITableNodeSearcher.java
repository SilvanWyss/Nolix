/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodesearcher;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface ITableNodeSearcher {
  /**
   * @param tableNode
   * @return the number of entity nodes of the given tableNode
   */
  int getEntityNodeCount(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @param entityId
   * @return a {@link Optional} with the entity node with the given entityId from
   *         the given tableNode if the given tableNode contains such an entity
   *         node, an empty {@link Optional} otherwise
   */
  Optional<? extends IMutableNode<?>> getOptionalStoredEntity(IMutableNode<?> tableNode, String entityId);

  /**
   * @param tableNode
   * @return the column nodes of the given tableNode
   */
  ExtendedIterable<? extends IMutableNode<?>> getStoredColumnNodes(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @param id
   * @return the entity node with the given id from the given tableNode
   * @throws RuntimeException if the given tableNode does not contain an entity
   *                          node with the given id
   */
  IMutableNode<?> getStoredEntityNode(IMutableNode<?> tableNode, String id);

  /**
   * @param tableNode
   * @return the entity nodes of the given tableNode
   */
  ExtendedIterable<? extends IMutableNode<?>> getStoredEntityNodes(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the id node of the given tableNode
   */
  IMutableNode<?> getStoredIdNode(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the name node of the given tableNode
   */
  IMutableNode<?> getStoredNameNode(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the id of the table of the given tableNode
   */
  String getTableId(IMutableNode<?> tableNode);

  /**
   * @param tableNode
   * @return the name of the table of the given tableNode
   */
  String getTableName(IMutableNode<?> tableNode);
}
