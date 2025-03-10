package ch.nolix.system.objectschema.model;

import ch.nolix.system.objectschema.rawschemadtomapper.ColumnDtoMapper;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

/**
 * @author Silvan Wyss
 * @version 2021-07-11
 */
public final class TableEditor {

  private static final ITableTool TABLE_TOOL = new TableTool();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private TableEditor() {
  }

  public static void addColumnToTable(final Table table, final Column column) {

    table.addColumnAttribute(column);
    column.setParentTableAttribute(table);

    if (table.isConnectedWithRealDatabase()) {

      final var tableName = table.getName();
      final var columnDtos = COLUMN_DTO_MAPPER.mapColumnToColumnDtos(column);

      //TODO: Add addColumns method to ISchemaAdapter
      for (final var c : columnDtos) {
        table.internalGetStoredRawSchemaAdapter().addColumn(tableName, c);
      }
    }

    table.internalSetEdited();
  }

  public static void deleteTable(final Table table) {

    if (table.belongsToDatabase()) {
      table.getStoredParentDatabase().removeTableAttribute(table);
    }

    final var tableName = table.getName();

    table.internalGetStoredRawSchemaAdapter().deleteTable(tableName);

    table.internalSetDeleted();
  }

  public static void setNameToTable(final Table table, final String name) {

    final var oldTableName = table.getName();
    final var referencingColumns = TABLE_TOOL.getStoredReferencingColumns(table);
    final var backReferencingColumns = TABLE_TOOL.getStoredBackReferencingColumns(table);

    table.setNameAttribute(name);

    if (table.isConnectedWithRealDatabase()) {

      table.internalGetStoredRawSchemaAdapter().setTableName(oldTableName, name);

      for (final var rc : referencingColumns) {
        ((Column) rc).internalSetContentModelToDatabase();
      }

      for (final var brc : backReferencingColumns) {
        ((Column) brc).internalSetContentModelToDatabase();
      }
    }

    table.internalSetEdited();
  }
}
