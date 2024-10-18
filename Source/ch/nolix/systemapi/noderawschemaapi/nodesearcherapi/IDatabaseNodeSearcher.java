package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IDatabaseNodeSearcher {

  IMutableNode<?> getStoredColumnNodeByColumnIdFromDatabaseNode(IMutableNode<?> databaseNode, String columnId);

  IMutableNode<?> getStoredDatabasePropertiesNodeFromDatabaseNode(IMutableNode<?> databaseNode);

  IMutableNode<?> getStoredEntityHeadsNodeFromDatabaseNode(IMutableNode<?> databaseNode);

  IMutableNode<?> getStoredTableNodeByTableIdFromDatabaseNode(
    IMutableNode<?> databaseNode,
    String tableId);

  IMutableNode<?> getStoredTableNodeByTableNameFromDatabaseNode(
    IMutableNode<?> databaseNode,
    String tableName);

  IContainer<? extends IMutableNode<?>> getStoredTableNodesFromDatabaseNode(IMutableNode<?> databaseNode);

  int getTableNodeCount(IMutableNode<?> databaseNode);
}
