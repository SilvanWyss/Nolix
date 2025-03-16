package ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IDatabaseNodeSearcher {

  String getDatabaseNameFromNodeDatabase(IMutableNode<?> nodeDatabase);

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
