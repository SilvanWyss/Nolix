package ch.nolix.system.noderawdata.adapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemidschema.adapter.NodeRawSchemaAdapter;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapterAndSchemaReader;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public final class NodeDataAdapterAndSchemaReader extends AbstractDataAdapterAndSchemaReader {

  private final IMutableNode<?> nodeDatabase;

  private NodeDataAdapterAndSchemaReader(final IMutableNode<?> nodeDatabase) {

    super(NodeDataAdapter.forNodeDatabase(nodeDatabase), NodeRawSchemaAdapter.forNodeDatabase(nodeDatabase));

    this.nodeDatabase = nodeDatabase;
  }

  public static NodeDataAdapterAndSchemaReader forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapterAndSchemaReader(nodeDatabase);
  }

  public static NodeDataAdapterAndSchemaReader forNodeDatabaseInFile(final String filePath) {

    final var nodeDatabase = new FileNode(filePath);

    return forNodeDatabase(nodeDatabase);
  }

  @Override
  public IDataAdapterAndSchemaReader createEmptyCopy() {
    return forNodeDatabase(nodeDatabase);
  }
}
