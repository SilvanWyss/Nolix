package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
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
