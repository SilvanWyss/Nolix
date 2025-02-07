package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IContentModelNodeMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

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
  public INode<?> mapColumnDtoToColumnNode(final ColumnDto columnDto) {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.COLUMN,
      mapColumnDtoToIdNode(columnDto),
      mapColumnDtoToNameNode(columnDto),
      mapColunDtoToContentModelNode(columnDto));
  }

  private Node mapColumnDtoToIdNode(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, column.id());
  }

  private Node mapColumnDtoToNameNode(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, column.name());
  }

  private INode<?> mapColunDtoToContentModelNode(final ColumnDto column) {
    return CONTENT_MODEL_NODE_MAPPER.mapContentModelDtoToNode(column.contentModel());
  }
}
