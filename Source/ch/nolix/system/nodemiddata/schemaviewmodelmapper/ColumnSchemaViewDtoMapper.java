package ch.nolix.system.nodemiddata.schemaviewmodelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper.IColumnSchemaViewDtoMapper;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IColumnNodeSearcher;

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
    final var fieldType = COLUMN_NODE_SEARCHER.getColumnFieldTypeFromColumnNode(columnNode);
    final var dataType = COLUMN_NODE_SEARCHER.getColumnDataTypeFromColumnNode(columnNode);

    return //
    new ColumnViewDto(
      id,
      name,
      oneBasedColumnOrdinalIndex,
      fieldType,
      dataType);
  }
}
