//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithNameCapturer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdatabase.databaseandschemaadapter.DataAndSchemaAdapter;
import ch.nolix.system.objectdata.data.DataAdapter;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

//class
public final class NodeDataAdapter extends DataAdapter {

  //attribute
  private final IMutableNode<?> nodeDatabase;

  //constructor
  private NodeDataAdapter(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final ISchema schema) {

    super(
      databaseName,
      NodeSchemaAdapter.forDatabaseNode(databaseName, nodeDatabase),
      schema,
      () -> DataAndSchemaAdapter.forNodeDatabase(nodeDatabase));

    this.nodeDatabase = nodeDatabase;
  }

  //static method
  public static WithNameCapturer<AndSchemaCapturer<ISchema, NodeDataAdapter>> forNodeDatabase(
    final IMutableNode<?> nodeDatabase) {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forNodeDatabase(nodeDatabase);
  }

  //static method
  public static NodeDataAdapter forDatabaseNameAndNodeDatabaseAndSchema(
    final String databaseName,
    final IMutableNode<?> nodeDatabase,
    final ISchema schema) {
    return new NodeDataAdapter(databaseName, nodeDatabase, schema);
  }

  //static method
  public static WithNameCapturer<AndSchemaCapturer<ISchema, NodeDataAdapter>> forTemporaryInMemoryDatabase() {
    return NodeDataAdapterBuilder.createNodeDataAdapter().forTemporaryInMemoryNodeDatabase();
  }

  //method
  @Override
  public DataAdapter getEmptyCopy() {
    return forNodeDatabase(nodeDatabase).withName(getDatabaseName()).andSchema(getSchema());
  }
}
