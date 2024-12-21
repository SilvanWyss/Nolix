package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IContentModelNodeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class ColumnNodeMapper implements IColumnNodeMapper {

  private static final IContentModelNodeMapper CONTENT_MODEL_NODE_MAPPER = new ContentModelNodeMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToNode(final ColumnDto columnDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalogue.COLUMN,
      createIdNodeFrom(columnDto),
      createNameNodeFrom(columnDto),
      createParameterizedFieldTypeNodeFrom(columnDto));
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
