/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.schemaviewmodelmapper;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper.IDatabaseSchemaViewDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class DatabaseSchemaViewDtoMapper implements IDatabaseSchemaViewDtoMapper {
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final TableSchemaViewDtoMapper TABLE_SCHEMA_VIEW_DTO_MAPPER = new TableSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseInfoDto mapTableNodeToTableViewDto(final IMutableNode<?> nodeDatabase) {
    final var databaseName = DATABASE_NODE_SEARCHER.getDatabaseNameFromNodeDatabase(nodeDatabase);
    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);
    final var tableSchemaViews = tableNodes.to(TABLE_SCHEMA_VIEW_DTO_MAPPER::mapTableNodeToTableViewDto);

    return new DatabaseInfoDto(databaseName, tableSchemaViews);
  }
}
