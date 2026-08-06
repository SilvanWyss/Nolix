/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.schemaviewloader;

import ch.nolix.system.midschemainfo.modelmapper.TableInfoDtoMapper;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.sqlmiddata.schemaviewloader.IDatabaseSchemaViewLoader;

/**
 * @author Silvan Wyss
 */
public final class DatabaseSchemaViewLoader implements IDatabaseSchemaViewLoader {
  private static final TableInfoDtoMapper TABLE_DEFINITION_MAPPER = new TableInfoDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseInfoDto loadDatabaseSchemaView(final String databaseName, final ISchemaReader schemaAdapter) {
    final var tables = schemaAdapter.loadTables();
    final var tableSchemaViews = tables.to(TABLE_DEFINITION_MAPPER::mapMidSchemaTableDtoToTableViewDto);

    return new DatabaseInfoDto(databaseName, tableSchemaViews);
  }
}
