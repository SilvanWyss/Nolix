//package declaration
package ch.nolix.system.noderawdatabase.databaseandschemaadapter;

//own imports
import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.baserawschema.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.noderawdatabase.dataadapter.DataAdapter;
import ch.nolix.system.noderawschema.schemaadapter.SchemaAdapter;

//class
public final class DataAndSchemaAdapter extends BaseDataAndSchemaAdapter {

  //constructor
  private DataAndSchemaAdapter(final IMutableNode<?> nodeDatabase) {
    super(DataAdapter.forNodeDatabase(nodeDatabase), SchemaAdapter.forDatabaseNode(nodeDatabase));
  }

  //static method
  public static DataAndSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAndSchemaAdapter(nodeDatabase);
  }

  //static method
  public static DataAndSchemaAdapter forNodeDatabaseInFile(final String filePath) {
    return new DataAndSchemaAdapter(new FileNode(filePath));
  }
}
