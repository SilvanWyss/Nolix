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
  String getDatabaseNameFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  ITime getSchemaTimestampFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredDatabaseNameNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);
}
