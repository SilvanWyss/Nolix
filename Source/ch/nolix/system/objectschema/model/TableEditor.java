/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.model;

import ch.nolix.system.objectschema.midschemamodelmapper.ColumnDtoMapper;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

/**
 * @author Silvan Wyss
 */
public final class TableEditor {
  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private TableEditor() {
  }

  /**
   * Adds the given column to the given table.
   * 
   * @param table
   * @param column
   */
  public static void addColumnToTable(final Table table, final Column column) {
    table.addColumnAttribute(column);
    column.setParentTableAttribute(table);

    if (table.isConnectedWithRealDatabase()) {
      final var midSchemaAdapter = table.getStoredMidSchemaAdapter();
      final var tableId = table.getId();
      final var tableName = table.getName();
      final var tableIdentification = new TableIdentification(tableId, tableName);
      final var columnDto = COLUMN_DTO_MAPPER.mapColumnToMidSchemaColumnDto(column);

      midSchemaAdapter.addColumn(tableIdentification, columnDto);
    }

    table.setEdited();
  }

  /**
   * Deletes the given table.
   * 
   * @param table
   */
  public static void deleteTable(final Table table) {
    if (table.belongsToDatabase()) {
      table.getStoredParentDatabase().removeTableAttribute(table);
    }

    final var tableName = table.getName();

    table.getStoredMidSchemaAdapter().deleteTable(tableName);

    table.setDeleted();
  }

  /**
   * Sets the given name to the given table.
   * 
   * @param table
   * @param name
   */
  public static void setNameToTable(final Table table, final String name) {
    final var oldName = table.getName();

    table.setNameAttribute(name);
    table.setEdited();

    if (table.isConnectedWithRealDatabase()) {
      table.getStoredMidSchemaAdapter().renameTable(oldName, name);
    }
  }
}
