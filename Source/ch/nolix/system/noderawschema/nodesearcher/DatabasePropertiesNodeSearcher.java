package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabasePropertiesNodeSearcher implements IDatabasePropertiesNodeSearcher {

  @Override
  public ITime getSchemaTimestampFromDatabasePropertiesNode(final IMutableNode<?> databasePropertiesNode) {

    final var schemaTimeStampNode = getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(schemaTimeStampNode);
  }

  @Override
  public IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(
    final IMutableNode<?> databasePropertiesNode) {
    return databasePropertiesNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.SCHEMA_TIMESTAMP);
  }
}
