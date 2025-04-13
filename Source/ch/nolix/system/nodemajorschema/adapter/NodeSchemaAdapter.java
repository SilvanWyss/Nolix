package ch.nolix.system.nodemajorschema.adapter;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.majorschema.adapter.AbstractSchemaAdapter;

public final class NodeSchemaAdapter extends AbstractSchemaAdapter {

  private NodeSchemaAdapter(final ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter midNodeSchemaAdapter) {
    super(
      NodeSchemaReader.withMidNodeSchemaAdapter(midNodeSchemaAdapter),
      NodeSchemaWriter.withMidNodeSchemaAdapter(midNodeSchemaAdapter));
  }

  public static NodeSchemaAdapter forFileNodeDatabase(final String filePath) {

    final var midNodeSchemaAdapter = //
    ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter.forFileNodeDatabase(filePath);

    return new NodeSchemaAdapter(midNodeSchemaAdapter);
  }

  public static NodeSchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var midNodeSchemaAdapter = //
    ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter.forNodeDatabase(nodeDatabase);

    return new NodeSchemaAdapter(midNodeSchemaAdapter);
  }
}
