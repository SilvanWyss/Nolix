//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

//class
final class TableMutationValidator {

  //constant
  private static final IDatabaseTool DATABASE_HELPER = new DatabaseTool();

  //constant
  private static final ITableTool TABLE_HELPER = new TableTool();

  //constant
  private static final IColumnTool COLUMN_HELPER = new ColumnTool();

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

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }
}
