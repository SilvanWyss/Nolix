package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IContentModelNodeMapper;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;

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
      NodeHeaderCatalog.COLUMN,
      createIdNodeFrom(columnDto),
      createNameNodeFrom(columnDto),
      mapContentModelDtoToNode(columnDto));
  }

  private Node createIdNodeFrom(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, column.id());
  }

  private Node createNameNodeFrom(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, column.name());
  }

  private INode<?> mapContentModelDtoToNode(final ColumnDto column) {
    return CONTENT_MODEL_NODE_MAPPER.mapContentModelDtoToNode(column.contentModel());
  }
}
