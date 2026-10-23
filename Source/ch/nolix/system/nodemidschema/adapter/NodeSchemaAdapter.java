/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.adapter;

import ch.nolix.base.document.node.FileNode;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.midschema.adapter.AbstractSchemaAdapter;
import ch.nolix.system.nodemidschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.nodemidschema.schemareader.SchemaReader;
import ch.nolix.system.nodemidschema.schemawriter.SchemaWriter;

/**
 * @author Silvan Wyss
 */
public final class NodeSchemaAdapter extends AbstractSchemaAdapter {
  private NodeSchemaAdapter(final IMutableNode<?> nodeDatabase) {
    super(
      DatabaseInitializer.forDatabaseNameAndNodeDatabase("database", nodeDatabase),
      () -> SchemaReader.forNodeDatabase(nodeDatabase),
      () -> SchemaWriter.forNodeDatabase(nodeDatabase));
  }

  /**
   * @param filePath
   * @return a new {@link NodeSchemaAdapter} for the node database in the file
   *         with the given filePath
   */
  public static NodeSchemaAdapter forFileNodeDatabase(final String filePath) {
    final var nodeDatabase = FileNode.withFilePath(filePath);

    return new NodeSchemaAdapter(nodeDatabase);
  }

  /**
   * @param nodeDatabase
   * @return a new {@link NodeSchemaAdapter} for the given nodeDatabase
   */
  public static NodeSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeSchemaAdapter(nodeDatabase);
  }
}
