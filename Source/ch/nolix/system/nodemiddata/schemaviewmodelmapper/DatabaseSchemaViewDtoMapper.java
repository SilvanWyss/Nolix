/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.schemaviewmodelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper.IDatabaseSchemaViewDtoMapper;
import ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper.ITableSchemaViewDtoMapper;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabaseNodeSearcher;

/**
 * @author Silvan Wyss
 */
public final class DatabaseSchemaViewDtoMapper implements IDatabaseSchemaViewDtoMapper {
  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final ITableSchemaViewDtoMapper TABLE_SCHEMA_VIEW_DTO_MAPPER = new TableSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseViewDto mapTableNodeToTableViewDto(final IMutableNode<?> nodeDatabase) {
    final var databaseName = DATABASE_NODE_SEARCHER.getDatabaseNameFromNodeDatabase(nodeDatabase);
    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);
    final var tableSchemaViews = tableNodes.to(TABLE_SCHEMA_VIEW_DTO_MAPPER::mapTableNodeToTableViewDto);

    return new DatabaseViewDto(databaseName, tableSchemaViews);
  }
}
