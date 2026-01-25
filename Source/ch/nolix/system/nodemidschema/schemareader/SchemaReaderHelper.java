/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.schemareader;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.modelmapper.ColumnDtoMapper;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.nodemidschema.modelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.nodemidschema.nodesearcher.ITableNodeSearcher;
import ch.nolix.systemapi.nodemidschema.schemareader.ISchemaReaderHelper;

/**
 * @author Silvan Wyss
 */
public final class SchemaReaderHelper implements ISchemaReaderHelper {
  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ColumnDto> loadColumnsFromTableNode(IMutableNode<?> tableNode) {
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
