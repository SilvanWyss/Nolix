package ch.nolix.system.noderawdata.dataandschemaadapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.baserawschema.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.noderawdata.dataadapter.DataAdapter;
import ch.nolix.system.noderawschema.schemaadapter.SchemaAdapter;

public final class DataAndSchemaAdapter extends BaseDataAndSchemaAdapter {

  private DataAndSchemaAdapter(final IMutableNode<?> nodeDatabase) {
    super(DataAdapter.forNodeDatabase(nodeDatabase), SchemaAdapter.forDatabaseNode(nodeDatabase));
  }

  public static DataAndSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAndSchemaAdapter(nodeDatabase);
  }

  public static DataAndSchemaAdapter forNodeDatabaseInFile(final String filePath) {
    return new DataAndSchemaAdapter(new FileNode(filePath));
  }
}
