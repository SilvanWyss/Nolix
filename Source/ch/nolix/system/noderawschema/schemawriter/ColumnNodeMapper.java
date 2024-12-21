package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.nodemapper.ContentModelNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IContentModelNodeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;

public final class ColumnNodeMapper {

  private static final IContentModelNodeMapper CONTENT_MODEL_NODE_MAPPER = new ContentModelNodeMapper();

  public Node createColumnNodeFrom(final ColumnDto column) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalogue.COLUMN,
      createIdNodeFrom(column),
      createNameNodeFrom(column),
      createParameterizedFieldTypeNodeFrom(column));
  }

  private Node createIdNodeFrom(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.ID, column.id());
  }

  private Node createNameNodeFrom(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.NAME, column.name());
  }

  private INode<?> createParameterizedFieldTypeNodeFrom(final ColumnDto column) {
    return CONTENT_MODEL_NODE_MAPPER.mapContentModelDtoToNode(column.contentModel());
  }
}
