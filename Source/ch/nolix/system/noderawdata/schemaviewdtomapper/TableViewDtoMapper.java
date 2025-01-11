package ch.nolix.system.noderawdata.schemaviewdtomapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.schemaviewdtomapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.noderawdataapi.schemaviewdtomapperapi.ITableViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public final class TableViewDtoMapper implements ITableViewDtoMapper {

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableViewDto mapTableNodeToTableViewDto(final IMutableNode<?> tableNode) {

    final var id = TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
    final var name = TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);
    final var columnNodes = TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);

    final var columnViews = //
    columnNodes.toWithOneBasedIndex((i, c) -> COLUMN_VIEW_DTO_MAPPER.mapColumnNodeToColumnViewDto(c, 2 + i));

    return new TableViewDto(id, name, columnViews);
  }
}
