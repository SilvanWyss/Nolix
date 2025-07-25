package ch.nolix.system.nodemiddata.adapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.middata.adapter.AbstractDataAdapterAndSchemaReader;
import ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;

public final class NodeDataAdapterAndSchemaReader extends AbstractDataAdapterAndSchemaReader {

  private final IMutableNode<?> nodeDatabase;

  private NodeDataAdapterAndSchemaReader(final IMutableNode<?> nodeDatabase) {

    super(NodeDataAdapter.forNodeDatabase(nodeDatabase), NodeSchemaAdapter.forNodeDatabase(nodeDatabase));

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
