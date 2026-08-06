/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.schemaviewmodelmapper;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.nodemiddata.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper.ITableSchemaViewDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class TableSchemaViewDtoMapper implements ITableSchemaViewDtoMapper {
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnSchemaViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableInfoDto mapTableNodeToTableViewDto(final IMutableNode<?> tableNode) {
    final var id = TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
    final var name = TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);
    final var columnNodes = TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);

    final var columnViews = //
    columnNodes.toWithOneBasedIndex(
      (i, c) -> //
      COLUMN_VIEW_DTO_MAPPER.mapColumnNodeToColumnViewDto(
        c,
        FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + i));

    return new TableInfoDto(id, name, columnViews);
  }
}
