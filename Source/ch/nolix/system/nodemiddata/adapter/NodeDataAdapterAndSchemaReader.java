/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.adapter;

import ch.nolix.base.document.node.FileNode;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.middata.adapter.AbstractDataAdapterAndSchemaReader;
import ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter;
import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;

/**
 * @author Silvan Wyss
 */
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
    final var nodeDatabase = FileNode.withFilePath(filePath);

    return forNodeDatabase(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DataAdapterAndSchemaReader createEmptyCopy() {
    return forNodeDatabase(nodeDatabase);
  }
}
