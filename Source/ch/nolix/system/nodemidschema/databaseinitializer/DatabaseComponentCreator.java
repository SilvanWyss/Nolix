/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.databaseinitializer;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemidschema.databaseinitializer.IDatabaseComponentCreator;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
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
      .addChildNode(nameNode)
      .addChildNodes(schemaTimestampNode);
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
      .addChildNode(ImmutableNode.withHeader(name));
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
