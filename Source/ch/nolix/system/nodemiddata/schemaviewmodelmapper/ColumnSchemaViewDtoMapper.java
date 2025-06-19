package ch.nolix.system.nodemiddata.schemaviewmodelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.systemapi.middataapi.schemaviewapi.ColumnViewDto;
import ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi.IColumnSchemaViewDtoMapper;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IColumnNodeSearcher;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class ColumnSchemaViewDtoMapper implements IColumnSchemaViewDtoMapper {

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
