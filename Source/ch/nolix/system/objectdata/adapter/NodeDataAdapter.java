package ch.nolix.system.objectdata.adapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.adapter.NodeDataAdapterAndSchemaReader;
import ch.nolix.system.objectdata.model.DataAdapter;
import ch.nolix.system.objectschema.adapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;

public final class NodeDataAdapter extends DataAdapter {

  private final IMutableNode<?> nodeDatabase;

  private NodeDataAdapter(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final ISchema schema) {

    super(
      databaseName,
      NodeSchemaAdapter.forNodeDatabase(databaseName, nodeDatabase),
      schema,
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
  public DataAdapter createEmptyCopy() {
    return forNodeDatabase(nodeDatabase).withName(getDatabaseName()).andSchema(getSchema());
  }
}
