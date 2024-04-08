//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

//class
final class TableMutationExecutor {

  //constant
  private static final ITableTool TABLE_TOOL = new TableTool();

  //method
  public void addColumnToTable(final Table table, final Column column) {

    table.addColumnAttribute(column);
    column.setParentTableAttribute(table);

    if (table.isLinkedWithRealDatabase()) {
      table.internalgetStoredRawSchemaAdapter().addColumnToTable(table, column);
    }

    table.internalSetEdited();
  }

  //method
  public void deleteTable(final Table table) {

    if (table.belongsToDatabase()) {
      table.getParentDatabase().removeTableAttribute(table);
    }

    table.internalgetStoredRawSchemaAdapter().deleteTable(table);

    table.internalSetDeleted();
  }

  //method
  public void setNameToTable(final Table table, final String name) {

    final var oldTableName = table.getName();
    final var referencingColumns = TABLE_TOOL.getStoredReferencingColumns(table);
    final var backReferencingColumns = TABLE_TOOL.getStoredBackReferencingColumns(table);

    table.setNameAttribute(name);

    if (table.isLinkedWithRealDatabase()) {

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
