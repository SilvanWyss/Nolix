package ch.nolix.system.nodemajorschema.adapter;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.majorschema.adapter.AbstractSchemaReader;

public final class NodeSchemaReader extends AbstractSchemaReader {

  private NodeSchemaReader(final ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter midNodeSchemaAdapter) {
    super(midNodeSchemaAdapter);
  }

  public static NodeSchemaReader forFileNodeDatabase(final String filePath) {

    final var midNodeSchemaAdapter = //
    ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter.forFileNodeDatabase(filePath);

    return withMidNodeSchemaAdapter(midNodeSchemaAdapter);
  }

  public static NodeSchemaReader forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var midNodeSchemaAdapter = //
    ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter.forNodeDatabase(nodeDatabase);

    return withMidNodeSchemaAdapter(midNodeSchemaAdapter);
  }

  public static NodeSchemaReader withMidNodeSchemaAdapter(
    final ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter midNodeSchemaAdapter) {
    return new NodeSchemaReader(midNodeSchemaAdapter);
  }
}
