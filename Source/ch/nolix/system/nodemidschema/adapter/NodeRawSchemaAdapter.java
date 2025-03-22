package ch.nolix.system.nodemidschema.adapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.midschema.adapter.AbstractSchemaAdapter;
import ch.nolix.system.nodemidschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.nodemidschema.schemareader.SchemaReader;
import ch.nolix.system.nodemidschema.schemawriter.SchemaWriter;

public final class NodeRawSchemaAdapter extends AbstractSchemaAdapter {

  private NodeRawSchemaAdapter(final IMutableNode<?> nodeDatabase) {
    super(
      DatabaseInitializer.forDatabaseNameAndNodeDatabase("database", nodeDatabase),
      () -> SchemaReader.forNodeDatabase(nodeDatabase),
      () -> SchemaWriter.forNodeDatabase(nodeDatabase));
  }

  public static NodeRawSchemaAdapter forFileNodeDatabase(final String filePath) {

    final var nodeDatabase = new FileNode(filePath);

    return new NodeRawSchemaAdapter(nodeDatabase);
  }

  public static NodeRawSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeRawSchemaAdapter(nodeDatabase);
  }
}
