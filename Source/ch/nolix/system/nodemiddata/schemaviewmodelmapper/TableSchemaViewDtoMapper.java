package ch.nolix.system.nodemiddata.schemaviewmodelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemiddata.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.midschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.nodemiddataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi.IColumnSchemaViewDtoMapper;
import ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi.ITableSchemaViewDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public final class TableSchemaViewDtoMapper implements ITableSchemaViewDtoMapper {

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnSchemaViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableViewDto mapTableNodeToTableViewDto(final IMutableNode<?> tableNode) {

    final var id = TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
    final var name = TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);
    final var columnNodes = TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);

    final var columnViews = //
    columnNodes.toWithOneBasedIndex(
      (i, c) -> //
      COLUMN_VIEW_DTO_MAPPER.mapColumnNodeToColumnViewDto(
        c,
        FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + i));

    return new TableViewDto(id, name, columnViews);
  }
}
