//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class TableMutationValidator {

  //constant
  private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();

  //constant
  private static final ITableHelper TABLE_HELPER = new TableHelper();

  //constant
  private static final IColumnHelper COLUMN_HELPER = new ColumnHelper();

  //method
  public void assertCanAddColumnToTable(final Table table, final Column column) {

    TABLE_HELPER.assertIsOpen(table);
    TABLE_HELPER.assertDoesNotContainColumnWithGivenName(table, column.getName());

    COLUMN_HELPER.assertIsOpen(column);
    COLUMN_HELPER.assertIsNew(column);

    if (COLUMN_HELPER.isAReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedReferenceType = (BaseParameterizedReferenceType) column.getParameterizedPropertyType();
      final var referencedTable = baseParameterizedReferenceType.getReferencedTable();

      DATABASE_HELPER.assertContainsGivenTable(table.getParentDatabase(), referencedTable);
    }

    if (COLUMN_HELPER.isABackReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedBackReferenceType = (BaseParameterizedBackReferenceType) column
        .getParameterizedPropertyType();

      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_HELPER.assertContainsTableWithGivenColumn(table.getParentDatabase(), backReferencedColumn);
    }
  }

  //method
  public void assertCanDeleteTable(final Table table) {
    TABLE_HELPER.assertIsOpen(table);
    TABLE_HELPER.assertIsNotNew(table);
    TABLE_HELPER.assertIsNotDeleted(table);
    TABLE_HELPER.assertIsNotReferenced(table);
  }

  //method
  public void assertCanSetNameToTable(final Table table, final String name) {

    TABLE_HELPER.assertIsOpen(table);

    if (table.belongsToDatabase()) {
      DATABASE_HELPER.assertDoesNotContainTableWithGivenName(table.getParentDatabase(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
  }
}
