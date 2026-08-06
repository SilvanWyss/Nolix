/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.schemareader;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.modelmapper.ColumnDtoMapper;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.nodemidschema.schemareader.ISchemaReaderHelper;

/**
 * @author Silvan Wyss
 */
public final class SchemaReaderHelper implements ISchemaReaderHelper {
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ColumnDto> loadColumnsFromTableNode(IMutableNode<?> tableNode) {
    final var columnNodes = TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);

    return columnNodes.to(COLUMN_DTO_MAPPER::mapColumnNodeToColumnDto);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto loadTableFromTableNode(IMutableNode<?> tableNode) {
    final var tableId = TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
    final var tableName = TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);
    final var columns = loadColumnsFromTableNode(tableNode);

    return new TableDto(tableId, tableName, columns);
  }
}
