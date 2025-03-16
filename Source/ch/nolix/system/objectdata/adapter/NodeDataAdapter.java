package ch.nolix.system.objectdata.adapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemiddata.adapter.NodeDataAdapterAndSchemaReader;
import ch.nolix.system.objectschema.adapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;

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

  public static WithNameCaptor<AndSchemaCaptor<IEntityTypeSet, NodeDataAdapter>> forNodeDatabase(
    final IMutableNode<?> nodeDatabase) {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forNodeDatabase(nodeDatabase);
  }

  public static NodeDataAdapter forDatabaseNameAndNodeDatabaseAndSchema(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final IEntityTypeSet entityTypeSet) {
    return new NodeDataAdapter(databaseName, nodeDatabase, entityTypeSet);
  }

  public static WithNameCaptor<AndSchemaCaptor<IEntityTypeSet, NodeDataAdapter>> forTemporaryInMemoryDatabase() {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forTemporaryInMemoryNodeDatabase();
  }

  @Override
  public AbstractDataAdapter createEmptyCopy() {
    return forNodeDatabase(nodeDatabase).withName(getDatabaseName()).andSchema(getSchema());
  }
}
