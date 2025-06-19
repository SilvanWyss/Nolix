package ch.nolix.system.sqlmiddata.schemaviewloader;

import ch.nolix.system.middata.schemaviewmapper.TableViewDtoMapper;
import ch.nolix.systemapi.middataapi.schemaviewapi.DatabaseViewDto;
import ch.nolix.systemapi.middataapi.schemaviewmapperapi.ITableViewDtoMapper;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlmiddataapi.schemaviewloaderapi.IDatabaseSchemaViewLoader;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public final class DatabaseSchemaViewLoader implements IDatabaseSchemaViewLoader {

  private static final ITableViewDtoMapper TABLE_DEFINITION_MAPPER = new TableViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseViewDto loadDatabaseSchemaView(final String databaseName, final ISchemaReader schemaAdapter) {

    final var tables = schemaAdapter.loadTables();
    final var tableSchemaViews = tables.to(TABLE_DEFINITION_MAPPER::mapMidSchemaTableDtoToTableViewDto);

    return new DatabaseViewDto(databaseName, tableSchemaViews);
  }
}
