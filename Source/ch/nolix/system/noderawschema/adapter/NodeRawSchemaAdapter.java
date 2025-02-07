package ch.nolix.system.noderawschema.adapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.noderawschema.schemareader.SchemaReader;
import ch.nolix.system.noderawschema.schemawriter.SchemaWriter;
import ch.nolix.system.rawschema.adapter.AbstractRawSchemaAdapter;

public final class NodeRawSchemaAdapter extends AbstractRawSchemaAdapter {

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
