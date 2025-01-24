package ch.nolix.system.noderawdata.schemaviewdtomapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.schemaviewdtomapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class ColumnViewDtoMapper implements IColumnViewDtoMapper {

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  @Override
  /**
   * {@inheritDoc}
   */
  public ColumnViewDto mapColumnNodeToColumnViewDto(
    final IMutableNode<?> columnNode,
    final int oneBasedColumnOrdinalIndex) {

    final var id = COLUMN_NODE_SEARCHER.getColumnIdFromColumnNode(columnNode);
    final var name = COLUMN_NODE_SEARCHER.getColumnNameFromColumnNode(columnNode);
    final var contentType = COLUMN_NODE_SEARCHER.getColumnContentTypeFromColumnNode(columnNode);
    final var dataType = COLUMN_NODE_SEARCHER.getColumnDataTypeFromColumnNode(columnNode);

    return //
    new ColumnViewDto(
      id,
      name,
      oneBasedColumnOrdinalIndex,
      contentType,
      dataType);
  }
}
