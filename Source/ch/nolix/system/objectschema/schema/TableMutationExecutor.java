package ch.nolix.system.objectschema.schema;

import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

final class TableMutationExecutor {

  private static final ITableTool TABLE_TOOL = new TableTool();

  public void addColumnToTable(final Table table, final Column column) {

    table.addColumnAttribute(column);
    column.setParentTableAttribute(table);

    if (table.isConnectedWithRealDatabase()) {
      table.internalgetStoredRawSchemaAdapter().addColumnToTable(table, column);
    }

    table.internalSetEdited();
  }

  public void deleteTable(final Table table) {

    if (table.belongsToDatabase()) {
      table.getStoredParentDatabase().removeTableAttribute(table);
    }

    table.internalgetStoredRawSchemaAdapter().deleteTable(table);

    table.internalSetDeleted();
  }

  public void setNameToTable(final Table table, final String name) {

    final var oldTableName = table.getName();
    final var referencingColumns = TABLE_TOOL.getStoredReferencingColumns(table);
    final var backReferencingColumns = TABLE_TOOL.getStoredBackReferencingColumns(table);

    table.setNameAttribute(name);

    if (table.isConnectedWithRealDatabase()) {

      table.internalgetStoredRawSchemaAdapter().setTableName(oldTableName, name);

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
