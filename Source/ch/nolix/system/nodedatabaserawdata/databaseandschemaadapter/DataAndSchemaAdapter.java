//package declaration
package ch.nolix.system.nodedatabaserawdata.databaseandschemaadapter;

//own imports
import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.dataadapter.DataAdapter;
import ch.nolix.system.nodedatabaserawschema.schemaadapter.SchemaAdapter;
import ch.nolix.system.rawdatabase.databaseandschemaadapter.BaseDataAndSchemaAdapter;

//class
public final class DataAndSchemaAdapter extends BaseDataAndSchemaAdapter {

  //static method
  public static DataAndSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAndSchemaAdapter(nodeDatabase);
  }

  //static method
  public static DataAndSchemaAdapter forNodeDatabaseInFile(final String filePath) {
    return new DataAndSchemaAdapter(new FileNode(filePath));
  }

  //constructor
  private DataAndSchemaAdapter(final IMutableNode<?> nodeDatabase) {
    super(DataAdapter.forNodeDatabase(nodeDatabase), SchemaAdapter.forDatabaseNode(nodeDatabase));
  }
}
