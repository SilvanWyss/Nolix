package ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDatabasePropertiesNodeSearcher {

  String getDatabaseNameFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  ITime getSchemaTimestampFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredDatabaseNameNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);
}
