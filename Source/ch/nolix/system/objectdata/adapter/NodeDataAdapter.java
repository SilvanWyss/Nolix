package ch.nolix.system.objectdata.adapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.adapter.NodeDataAdapterAndSchemaReader;
import ch.nolix.system.objectdata.model.AbstractDataAdapter;
import ch.nolix.system.objectschema.adapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;

public final class NodeDataAdapter extends AbstractDataAdapter {

  private final IMutableNode<?> nodeDatabase;

  private NodeDataAdapter(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final ISchema schema) {

    super(
      databaseName,
      schema,
      NodeSchemaAdapter.forNodeDatabase(databaseName, nodeDatabase),
      () -> NodeDataAdapterAndSchemaReader.forNodeDatabase(nodeDatabase));

    this.nodeDatabase = nodeDatabase;
  }

  public static WithNameCaptor<AndSchemaCaptor<ISchema, NodeDataAdapter>> forNodeDatabase(
    final IMutableNode<?> nodeDatabase) {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forNodeDatabase(nodeDatabase);
  }

  public static NodeDataAdapter forDatabaseNameAndNodeDatabaseAndSchema(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final ISchema schema) {
    return new NodeDataAdapter(databaseName, nodeDatabase, schema);
  }

  public static WithNameCaptor<AndSchemaCaptor<ISchema, NodeDataAdapter>> forTemporaryInMemoryDatabase() {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forTemporaryInMemoryNodeDatabase();
  }

  @Override
  public AbstractDataAdapter createEmptyCopy() {
    return forNodeDatabase(nodeDatabase).withName(getDatabaseName()).andSchema(getSchema());
  }
}
