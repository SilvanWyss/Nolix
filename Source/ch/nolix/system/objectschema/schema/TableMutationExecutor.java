//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class TableMutationExecutor {

  // constant
  private static final ITableHelper TABLE_HELPER = new TableHelper();

  // method
  public void addColumnToTable(final Table table, final Column column) {

    table.addColumnAttribute(column);
    column.setParentTableAttribute(table);

    if (table.isLinkedWithRealDatabase()) {
      table.internalgetStoredRawSchemaAdapter().addColumnToTable(table, column);
    }

    table.internalSetEdited();
  }

  // method
  public void deleteTable(final Table table) {

    if (table.belongsToDatabase()) {
      table.getParentDatabase().removeTableAttribute(table);
    }

    table.internalgetStoredRawSchemaAdapter().deleteTable(table);

    table.internalSetDeleted();
  }

  // method
  public void setNameToTable(final Table table, final String name) {

    final var oldTableName = table.getName();
    final var referencingColumns = TABLE_HELPER.getStoredReferencingColumns(table);
    final var backReferencingColumns = TABLE_HELPER.getStoredBackReferencingColumns(table);

    table.setNameAttribute(name);

    if (table.isLinkedWithRealDatabase()) {

      table.internalgetStoredRawSchemaAdapter().setTableName(oldTableName, name);

      for (final var rc : referencingColumns) {
        ((Column) rc).setParameterizedPropertyTypeToDatabase();
      }

      for (final var brc : backReferencingColumns) {
        ((Column) brc).setParameterizedPropertyTypeToDatabase();
      }
    }

    table.internalSetEdited();
  }
}
