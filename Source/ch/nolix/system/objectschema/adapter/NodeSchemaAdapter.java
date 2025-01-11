package ch.nolix.system.objectschema.adapter;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.RegularExpressionPatternCatalog;

public final class NodeSchemaAdapter extends AbstractSchemaAdapter {

  private NodeSchemaAdapter(final String databaseName, final IMutableNode<?> nodeDatabase) {
    super(databaseName, ch.nolix.system.noderawschema.adapter.NodeRawSchemaAdapter.forNodeDatabase(nodeDatabase));
  }

  public static NodeSchemaAdapter forNodeDatabase(final String databaseName, final IMutableNode<?> nodeDatabase) {
    return new NodeSchemaAdapter(databaseName, nodeDatabase);
  }

  public static NodeSchemaAdapter forFileNodeDatabase(final String filePath) {

    final var fileName = new FileAccessor(filePath).getName();
    final var databaseName = RegularExpressionPatternCatalog.DOT_PATTERN.split(fileName)[0];
    final var nodeDatabase = MutableNode.fromFile(filePath);

    return new NodeSchemaAdapter(databaseName, nodeDatabase);
  }
}
