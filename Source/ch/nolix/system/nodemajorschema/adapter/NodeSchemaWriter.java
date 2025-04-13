package ch.nolix.system.nodemajorschema.adapter;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.majorschema.adapter.AbstractSchemaWriter;

public final class NodeSchemaWriter extends AbstractSchemaWriter {

  private NodeSchemaWriter(final ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter midNodeSchemaAdapter) {
    super(midNodeSchemaAdapter);
  }

  public static NodeSchemaWriter forFileNodeDatabase(final String filePath) {

    final var midNodeSchemaAdapter = //
    ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter.forFileNodeDatabase(filePath);

    return withMidNodeSchemaAdapter(midNodeSchemaAdapter);
  }

  public static NodeSchemaWriter forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var midNodeSchemaAdapter = //
    ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter.forNodeDatabase(nodeDatabase);

    return withMidNodeSchemaAdapter(midNodeSchemaAdapter);
  }

  public static NodeSchemaWriter withMidNodeSchemaAdapter(
    final ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter midNodeSchemaAdapter) {
    return new NodeSchemaWriter(midNodeSchemaAdapter);
  }
}
