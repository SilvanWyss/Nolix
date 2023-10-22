//package declaration
package ch.nolix.system.objectdatabase.dataadapter;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.databaseandschemaadapter.DataAndSchemaAdapter;
import ch.nolix.system.objectdatabase.database.DataAdapter;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class NodeDataAdapter extends DataAdapter {

  //attribute
  private final IMutableNode<?> nodeDatabase;

  //constructor
  NodeDataAdapter(
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
  public static NodeDataAdapterBuilder forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapterBuilder(nodeDatabase);
  }

  //method
  @Override
  public DataAdapter getEmptyCopy() {
    return forNodeDatabase(nodeDatabase).withName(getDatabaseName()).andSchema(getSchema());
  }
}
