package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface ITableNodeSearcher {

  IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(IMutableNode<?> tableNode, String columnName);

  IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredIdNodeFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredNameNodeFromTableNode(IMutableNode<?> tableNode);

  String getTableIdFromTableNode(IMutableNode<?> tableNode);

  String getTableNameFromTableNode(IMutableNode<?> tableNode);
}
