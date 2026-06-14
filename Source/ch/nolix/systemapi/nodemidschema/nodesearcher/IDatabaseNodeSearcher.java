/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.IMutableNode;

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

  IWellOrderContainer<? extends IMutableNode<?>> getStoredTableNodesFromNodeDatabase(IMutableNode<?> nodeDatabase);

  int getTableNodeCount(IMutableNode<?> nodeDatabase);
}
