package ch.nolix.system.noderawdata.adapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.adapter.NodeRawSchemaAdapter;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapterAndSchemaReader;

public final class NodeDataAdapterAndSchemaReader extends AbstractDataAdapterAndSchemaReader {

  private NodeDataAdapterAndSchemaReader(final IMutableNode<?> nodeDatabase) {
    super(NodeDataAdapter.forNodeDatabase(nodeDatabase), NodeRawSchemaAdapter.forNodeDatabase(nodeDatabase));
  }

  public static NodeDataAdapterAndSchemaReader forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapterAndSchemaReader(nodeDatabase);
  }

  public static NodeDataAdapterAndSchemaReader forNodeDatabaseInFile(final String filePath) {
    return new NodeDataAdapterAndSchemaReader(new FileNode(filePath));
  }
}
