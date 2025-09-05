package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.time.moment.ITime;

public interface IDatabasePropertiesNodeSearcher {
  String getDatabaseNameFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  ITime getSchemaTimestampFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredDatabaseNameNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);
}
