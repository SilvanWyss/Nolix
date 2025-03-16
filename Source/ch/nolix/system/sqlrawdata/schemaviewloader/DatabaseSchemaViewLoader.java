package ch.nolix.system.sqlrawdata.schemaviewloader;

import ch.nolix.system.rawdata.schemaviewmapper.TableSchemaViewDtoMapper;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.ITableSchemaViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.sqlrawdataapi.schemaviewloaderapi.IDatabaseSchemaViewLoader;

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
