package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDatabasePropertiesNodeSearcher {

  String getNameFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  ITime getSchemaTimestampFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredNameNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);
}
