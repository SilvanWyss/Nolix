package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi.IDatabaseComponentCreator;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class DatabaseComponentCreator implements IDatabaseComponentCreator {

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> createDatabasePropertiesNodeWithDatabaseNameAndInitialSchemaTimeStamp(
    final String databaseName,
    final ITime initialSchemaTimeStamp) {

    final var nameNode = createNameNodeWithName(databaseName);
    final var schemaTimestampNode = createSchemaTimestampNodeWithInitialSchemaTimeStamp(initialSchemaTimeStamp);

    return //
    MutableNode
      .createEmpty()
      .setHeader(NodeHeaderCatalog.DATABASE_PROPERTIES)
      .addChildNode(nameNode, schemaTimestampNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> createEntityIndexesNode() {
    return MutableNode.createEmpty().setHeader(NodeHeaderCatalog.ENTITY_INDEXES);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> createNameNodeWithName(final String name) {
    return MutableNode
      .createEmpty()
      .setHeader(NodeHeaderCatalog.NAME)
      .addChildNode(Node.withHeader(name));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> createSchemaTimestampNodeWithInitialSchemaTimeStamp(final ITime initialSchemaTimeStamp) {
    return //
    MutableNode
      .createEmpty()
      .setHeader(NodeHeaderCatalog.SCHEMA_TIMESTAMP)
      .addChildNode(initialSchemaTimeStamp.getSpecification().getStoredSingleChildNode());
  }
}
