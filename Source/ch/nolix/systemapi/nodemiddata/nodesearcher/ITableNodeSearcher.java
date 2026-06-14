/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodesearcher;

import java.util.Optional;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface ITableNodeSearcher {
  int getEntityNodeCountOfTableNode(IMutableNode<?> tableNode);

  Optional<? extends IMutableNode<?>> getOptionalStoredEntityNodeFromTableNode(
    IMutableNode<?> tableNode,
    String id);

  IWellOrderContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredEntityNodeFromTableNode(IMutableNode<?> tableNode, String id);

  IWellOrderContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredIdNodeFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredNameNodeFromTableNode(IMutableNode<?> tableNode);

  String getTableIdFromTableNode(IMutableNode<?> tableNode);

  String getTableNameFromTableNode(IMutableNode<?> tableNode);
}
