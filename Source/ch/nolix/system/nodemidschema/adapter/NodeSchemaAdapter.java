package ch.nolix.system.nodemidschema.adapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.midschema.adapter.AbstractSchemaAdapter;
import ch.nolix.system.nodemidschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.nodemidschema.schemareader.SchemaReader;
import ch.nolix.system.nodemidschema.schemawriter.SchemaWriter;

public final class NodeSchemaAdapter extends AbstractSchemaAdapter {
  private NodeSchemaAdapter(final IMutableNode<?> nodeDatabase) {
    super(
      DatabaseInitializer.forDatabaseNameAndNodeDatabase("database", nodeDatabase),
      () -> SchemaReader.forNodeDatabase(nodeDatabase),
      () -> SchemaWriter.forNodeDatabase(nodeDatabase));
  }

  public static NodeSchemaAdapter forFileNodeDatabase(final String filePath) {
    final var nodeDatabase = new FileNode(filePath);

    return new NodeSchemaAdapter(nodeDatabase);
  }

  public static NodeSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeSchemaAdapter(nodeDatabase);
  }
}
