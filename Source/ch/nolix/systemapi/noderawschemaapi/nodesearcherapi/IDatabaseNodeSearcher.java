package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IDatabaseNodeSearcher {

  IMutableNode<?> getStoredColumnNodeByColumnIdFromNodeDatabase(IMutableNode<?> nodeDatabase, String columnId);

  IMutableNode<?> getStoredDatabasePropertiesNodeFromNodeDatabase(IMutableNode<?> nodeDatabase);

  IMutableNode<?> getStoredEntityIndexesNodeFromNodeDatabase(IMutableNode<?> nodeDatabase);

  IMutableNode<?> getStoredTableNodeByTableIdFromNodeDatabase(
    IMutableNode<?> nodeDatabase,
    String tableId);

  IMutableNode<?> getStoredTableNodeByTableNameFromNodeDatabase(
    IMutableNode<?> nodeDatabase,
    String tableName);

  IContainer<? extends IMutableNode<?>> getStoredTableNodesFromNodeDatabase(IMutableNode<?> nodeDatabase);

  int getTableNodeCount(IMutableNode<?> nodeDatabase);
}
