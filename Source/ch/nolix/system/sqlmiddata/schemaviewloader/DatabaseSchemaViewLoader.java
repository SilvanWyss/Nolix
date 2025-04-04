package ch.nolix.system.sqlmiddata.schemaviewloader;

import ch.nolix.system.middata.schemaviewmapper.TableSchemaViewDtoMapper;
import ch.nolix.systemapi.middataapi.schemaviewmapperapi.ITableSchemaViewDtoMapper;
import ch.nolix.systemapi.middataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlmiddataapi.schemaviewloaderapi.IDatabaseSchemaViewLoader;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public final class DatabaseSchemaViewLoader implements IDatabaseSchemaViewLoader {

  private static final ITableSchemaViewDtoMapper TABLE_DEFINITION_MAPPER = new TableSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseSchemaViewDto loadDatabaseSchemaView(final String databaseName, final ISchemaReader schemaAdapter) {

    final var tables = schemaAdapter.loadTables();
    final var tableSchemaViews = tables.to(TABLE_DEFINITION_MAPPER::mapTableDtoToTableSchemaViewDto);

    return new DatabaseSchemaViewDto(databaseName, tableSchemaViews);
  }
}
