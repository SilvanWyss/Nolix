package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface ITableNodeSearcher {
  int getOneBasedIndexOfColumnInTableNodeByColumnName(IMutableNode<?> tableNode, String columnName);

  IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnId(IMutableNode<?> tableNode, String columnId);

  IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(IMutableNode<?> tableNode, String columnName);

  IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(IMutableNode<?> tableNode);

  IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredIdNodeFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredNameNodeFromTableNode(IMutableNode<?> tableNode);

  String getTableIdFromTableNode(IMutableNode<?> tableNode);

  String getTableNameFromTableNode(IMutableNode<?> tableNode);
}
