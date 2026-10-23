/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseNodeSearcher {
  /**
   * @param nodeDatabase
   * @return the database name from the given nodeDatabase
   */
  String getDatabaseNameFromNodeDatabase(IMutableNode<?> nodeDatabase);

  /**
   * @param nodeDatabase
   * @param columnId
   * @return the column node with the given columnId from the given nodeDatabase
   * @throws RuntimeException if the given nodeDatabase does not contain a column
   *                          node with the given columnId
   */
  IMutableNode<?> getStoredColumnNodeByColumnIdFromNodeDatabase(IMutableNode<?> nodeDatabase, String columnId);

  /**
   * @param nodeDatabase
   * @return the database properties node from the given nodeDatabase
   */
  IMutableNode<?> getStoredDatabasePropertiesNodeFromNodeDatabase(IMutableNode<?> nodeDatabase);

  /**
   * @param nodeDatabase
   * @return the entity indexes node from the given nodeDatabase
   */
  IMutableNode<?> getStoredEntityIndexesNodeFromNodeDatabase(IMutableNode<?> nodeDatabase);

  /**
   * @param nodeDatabase
   * @param tableId
   * @return the table node with the given tableId from the given nodeDatabase
   * @throws RuntimeException if the given nodeDatabase does not contain a table
   *                          node with the given tableId
   */
  IMutableNode<?> getStoredTableNodeByTableIdFromNodeDatabase(
    IMutableNode<?> nodeDatabase,
    String tableId);

  /**
   * @param nodeDatabase
   * @param tableName
   * @return the table node with the given tableName from the given nodeDatabase
   * @throws RuntimeException if the given nodeDatabase does not contain a table
   *                          node with the given tableName
   */
  IMutableNode<?> getStoredTableNodeByTableNameFromNodeDatabase(
    IMutableNode<?> nodeDatabase,
    String tableName);

  /**
   * @param nodeDatabase
   * @return the table nodes from the given nodeDatabase
   */
  ExtendedIterable<? extends IMutableNode<?>> getStoredTableNodesFromNodeDatabase(IMutableNode<?> nodeDatabase);

  /**
   * @param nodeDatabase
   * @return the number of table nodes of the given nodeDatabase
   */
  int getTableNodeCount(IMutableNode<?> nodeDatabase);
}
