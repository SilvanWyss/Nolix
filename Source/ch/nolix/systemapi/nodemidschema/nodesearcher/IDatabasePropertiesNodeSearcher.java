/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public interface IDatabasePropertiesNodeSearcher {
  /**
   * @param databasePropertiesNode
   * @return the database name from the given databasePropertiesNode
   */
  String getDatabaseNameFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  /**
   * @param databasePropertiesNode
   * @return the schema timestamp from the given databasePropertiesNode
   */
  ITime getSchemaTimestampFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  /**
   * @param databasePropertiesNode
   * @return the database name node from the given databasePropertiesNode
   */
  IMutableNode<?> getStoredDatabaseNameNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  /**
   * @param databasePropertiesNode
   * @return the schema timestamp node from the given databasePropertiesNode
   */
  IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);
}
