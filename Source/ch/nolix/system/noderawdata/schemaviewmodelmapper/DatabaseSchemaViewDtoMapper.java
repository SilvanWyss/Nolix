package ch.nolix.system.noderawdata.schemaviewmodelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.schemaviewmodelmapperapi.IDatabaseSchemaViewDtoMapper;
import ch.nolix.systemapi.noderawdataapi.schemaviewmodelmapperapi.ITableSchemaViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public final class DatabaseSchemaViewDtoMapper implements IDatabaseSchemaViewDtoMapper {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final ITableSchemaViewDtoMapper TABLE_SCHEMA_VIEW_DTO_MAPPER = new TableSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseSchemaViewDto mapTableNodeToTableViewDto(final IMutableNode<?> nodeDatabase) {

    final var databaseName = DATABASE_NODE_SEARCHER.getDatabaseNameFromNodeDatabase(nodeDatabase);
    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);
    final var tableSchemaViews = tableNodes.to(TABLE_SCHEMA_VIEW_DTO_MAPPER::mapTableNodeToTableViewDto);

    return new DatabaseSchemaViewDto(databaseName, tableSchemaViews);
  }
}
