/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.adapter;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.nodemiddata.adapter.NodeDataAdapterAndSchemaReader;
import ch.nolix.system.objectschema.adapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;

/**
 * @author Silvan Wyss
 */
public final class NodeDataAdapter extends AbstractDataAdapter {
  private final IMutableNode<?> nodeDatabase;

  private NodeDataAdapter(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final IEntityTypeSet entityTypeSet) {
    super(
      databaseName,
      entityTypeSet,
      NodeSchemaAdapter.forNodeDatabase(databaseName, nodeDatabase),
      () -> NodeDataAdapterAndSchemaReader.forNodeDatabase(nodeDatabase));

    this.nodeDatabase = nodeDatabase;
  }

  /**
   * @param nodeDatabase
   * @return a {@link WithNameCaptor} to define the name and the schema of a new
   *         {@link NodeDataAdapter} for the given nodeDatabase
   * @throws RuntimeException if the given nodeDatabase is null
   */
  public static WithNameCaptor<AndSchemaCaptor<IEntityTypeSet, NodeDataAdapter>> forNodeDatabase(
    final IMutableNode<?> nodeDatabase) {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forNodeDatabase(nodeDatabase);
  }

  /**
   * @param databaseName
   * @param nodeDatabase
   * @param entityTypeSet
   * @return a new {@link NodeDataAdapter} for the database with the given
   *         databaseName in the given nodeDatabase, that uses the given
   *         entityTypeSet
   */
  public static NodeDataAdapter forDatabaseNameAndNodeDatabaseAndSchema(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final IEntityTypeSet entityTypeSet) {
    return new NodeDataAdapter(databaseName, nodeDatabase, entityTypeSet);
  }

  /**
   * @return a {@link WithNameCaptor} to define the name and the schema of a new
   *         {@link NodeDataAdapter} for a temporary in-memory database
   */
  public static WithNameCaptor<AndSchemaCaptor<IEntityTypeSet, NodeDataAdapter>> forTemporaryInMemoryDatabase() {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forTemporaryInMemoryNodeDatabase();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractDataAdapter createEmptyCopy() {
    return forNodeDatabase(nodeDatabase).withName(getDatabaseName()).andSchema(getSchema());
  }
}
