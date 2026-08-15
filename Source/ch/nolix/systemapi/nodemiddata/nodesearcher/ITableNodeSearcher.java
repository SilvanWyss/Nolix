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
  int getEntityNodeCount(IMutableNode<?> tableNode);

  Optional<? extends IMutableNode<?>> getOptionalStoredEntity(IMutableNode<?> tableNode, String entityId);

  ExtendedIterable<? extends IMutableNode<?>> getStoredColumnNodes(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredEntityNode(IMutableNode<?> tableNode, String id);

  ExtendedIterable<? extends IMutableNode<?>> getStoredEntityNodes(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredIdNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredNameNode(IMutableNode<?> tableNode);

  String getTableId(IMutableNode<?> tableNode);

  String getTableName(IMutableNode<?> tableNode);
}
