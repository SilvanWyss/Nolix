package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class DatabasePropertiesNodeSearcher implements IDatabasePropertiesNodeSearcher {
  @Override
  public String getDatabaseNameFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode) {
    final var databaseNameNode = getStoredDatabaseNameNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return databaseNameNode.getSingleChildNodeHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITime getSchemaTimestampFromDatabasePropertiesNode(final IMutableNode<?> databasePropertiesNode) {
    final var schemaTimeStampNode = getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(schemaTimeStampNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredDatabaseNameNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode) {
    return databasePropertiesNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(
    final IMutableNode<?> databasePropertiesNode) {
    return databasePropertiesNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.SCHEMA_TIMESTAMP);
  }
}
