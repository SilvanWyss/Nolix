package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalog;
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
  public IMutableNode<?> createDatabasePropertiesNodeWithInitialSchemaTimeStamp(final ITime initialSchemaTimeStamp) {
    return //
    MutableNode
      .createEmpty()
      .setHeader(NodeHeaderCatalog.DATABASE_PROPERTIES)
      .addChildNode(createSchemaTimestampNodeWithInitialSchemaTimeStamp(initialSchemaTimeStamp));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> createEntityHeadsNode() {
    return MutableNode.createEmpty().setHeader(StructureHeaderCatalog.ENTITY_HEADS);
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
