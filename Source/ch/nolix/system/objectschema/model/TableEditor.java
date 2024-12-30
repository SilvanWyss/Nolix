package ch.nolix.system.objectschema.model;

import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

/**
 * @author Silvan Wyss
 * @version 2021-07-11
 */
public final class TableEditor {

  private static final ITableTool TABLE_TOOL = new TableTool();

  public void addColumnToTable(final Table table, final Column column) {

    table.addColumnAttribute(column);
    column.setParentTableAttribute(table);

    if (table.isConnectedWithRealDatabase()) {
      table.internalGetStoredRawSchemaAdapter().addColumnToTable(table, column);
    }

    table.internalSetEdited();
  }

  public void deleteTable(final Table table) {

    if (table.belongsToDatabase()) {
      table.getStoredParentDatabase().removeTableAttribute(table);
    }

    table.internalGetStoredRawSchemaAdapter().deleteTable(table);

    table.internalSetDeleted();
  }

  public void setNameToTable(final Table table, final String name) {

    final var oldTableName = table.getName();
    final var referencingColumns = TABLE_TOOL.getStoredReferencingColumns(table);
    final var backReferencingColumns = TABLE_TOOL.getStoredBackReferencingColumns(table);

    table.setNameAttribute(name);

    if (table.isConnectedWithRealDatabase()) {

      table.internalGetStoredRawSchemaAdapter().setTableName(oldTableName, name);

      for (final var rc : referencingColumns) {
        ((Column) rc).setParameterizedFieldTypeToDatabase();
      }

      for (final var brc : backReferencingColumns) {
        ((Column) brc).setParameterizedFieldTypeToDatabase();
      }
    }

    table.internalSetEdited();
  }
}
