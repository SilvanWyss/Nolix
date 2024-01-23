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
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final ITableTool TABLE_TOOL = new TableTool();

  //constant
  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  //method
  public void assertCanAddColumnToTable(final Table table, final Column column) {

    TABLE_TOOL.assertIsOpen(table);
    TABLE_TOOL.assertDoesNotContainColumnWithGivenName(table, column.getName());

    COLUMN_TOOL.assertIsOpen(column);
    COLUMN_TOOL.assertIsNew(column);

    if (COLUMN_TOOL.isAReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedReferenceType = (BaseParameterizedReferenceType) column.getParameterizedPropertyType();
      final var referencedTable = baseParameterizedReferenceType.getReferencedTable();

      DATABASE_TOOL.assertContainsGivenTable(table.getParentDatabase(), referencedTable);
    }

    if (COLUMN_TOOL.isABackReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedBackReferenceType = (BaseParameterizedBackReferenceType) column
        .getParameterizedPropertyType();

      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_TOOL.assertContainsTableWithGivenColumn(table.getParentDatabase(), backReferencedColumn);
    }
  }

  //method
  public void assertCanDeleteTable(final Table table) {
    TABLE_TOOL.assertIsOpen(table);
    TABLE_TOOL.assertIsNotNew(table);
    TABLE_TOOL.assertIsNotDeleted(table);
    TABLE_TOOL.assertIsNotReferenced(table);
  }

  //method
  public void assertCanSetNameToTable(final Table table, final String name) {

    TABLE_TOOL.assertIsOpen(table);

    if (table.belongsToDatabase()) {
      DATABASE_TOOL.assertDoesNotContainTableWithGivenName(table.getParentDatabase(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }
}
