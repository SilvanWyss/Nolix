package ch.nolix.system.noderawdata.dataandschemaadapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.adapter.NodeDataAdapter;
import ch.nolix.system.noderawschema.schemaadapter.SchemaAdapter;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapterAndSchemaReader;

public final class DataAndSchemaAdapter extends AbstractDataAdapterAndSchemaReader {

  private DataAndSchemaAdapter(final IMutableNode<?> nodeDatabase) {
    super(NodeDataAdapter.forNodeDatabase(nodeDatabase), SchemaAdapter.forNodeDatabase(nodeDatabase));
  }

  public static DataAndSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAndSchemaAdapter(nodeDatabase);
  }

  public static DataAndSchemaAdapter forNodeDatabaseInFile(final String filePath) {
    return new DataAndSchemaAdapter(new FileNode(filePath));
  }
}
