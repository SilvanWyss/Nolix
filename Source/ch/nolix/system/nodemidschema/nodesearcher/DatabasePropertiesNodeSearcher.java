package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabasePropertiesNodeSearcher implements IDatabasePropertiesNodeSearcher {

  @Override
  public String getDatabaseNameFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode) {

    final var databaseNameNode = getStoredDatabaseNameNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return databaseNameNode.getSingleChildNodeHeader();
  }

  @Override
  public ITime getSchemaTimestampFromDatabasePropertiesNode(final IMutableNode<?> databasePropertiesNode) {

    final var schemaTimeStampNode = getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(schemaTimeStampNode);
  }

  @Override
  public IMutableNode<?> getStoredDatabaseNameNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode) {
    return databasePropertiesNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }

  @Override
  public IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(
    final IMutableNode<?> databasePropertiesNode) {
    return databasePropertiesNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.SCHEMA_TIMESTAMP);
  }
}
