package ch.nolix.system.noderawschema.dtomapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.dtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ContentModelDtoMapper CONTENT_MODEL_DTO_MAPPER = new ContentModelDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapColumnNodeToColumnDto(final IMutableNode<?> columnNode) {

    final var columnId = COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
    final var columnName = COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
    final var contentModelNode = COLUMN_NODE_SEARCHER.getStoredContentModelNodeFromColumnNode(columnNode);
    final var contentModel = CONTENT_MODEL_DTO_MAPPER.mapContentModelNodeToContentModelDto(contentModelNode);

    return new ColumnDto(columnId, columnName, contentModel);
  }
}
