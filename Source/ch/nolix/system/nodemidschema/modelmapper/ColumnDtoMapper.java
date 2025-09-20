package ch.nolix.system.nodemidschema.modelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.nodemidschema.modelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IColumnNodeSearcher;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {
  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapColumnNodeToColumnDto(final IMutableNode<?> columnNode) {
    final var columnId = COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
    final var columnName = COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
    final var fieldType = COLUMN_NODE_SEARCHER.getColumnFieldTypeFromColumnNode(columnNode);
    final var dataType = COLUMN_NODE_SEARCHER.getColumnDataTypeFromColumnNode(columnNode);
    final var referenceableTableIds = COLUMN_NODE_SEARCHER.getReferenceableTableIdsFromColumnNode(columnNode);
    final var backReferenceableColumnIds = COLUMN_NODE_SEARCHER.getBackReferenceableColumnIdsFromColumnNode(columnNode);

    return new ColumnDto(columnId, columnName, fieldType, dataType, referenceableTableIds, backReferenceableColumnIds);
  }
}
