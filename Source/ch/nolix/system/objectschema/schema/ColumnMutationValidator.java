//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.system.objectschema.schematool.ParameterizedPropertyTypeTool;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedPropertyTypeTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

//class
final class ColumnMutationValidator {

  //constant
  private static final IDatabaseTool DATABASE_HELPER = new DatabaseTool();

  //constant
  private static final ITableTool TABLE_HELPER = new TableTool();

  //constant
  private static final IColumnTool COLUMN_HELPER = new ColumnTool();

  //constant
  private static final IParameterizedPropertyTypeTool PARAMETERIZED_PROPERTY_TYPE_HELPER = //
  new ParameterizedPropertyTypeTool();

  //method
  public void assertCanDeleteColumn(final Column column) {
    COLUMN_HELPER.assertIsOpen(column);
    COLUMN_HELPER.assertIsNotDeleted(column);
    column.assertIsNotBackReferenced();
  }

  //method
  public void assertCanSetNameToColumn(final Column column, final String name) {

    COLUMN_HELPER.assertIsOpen(column);

    if (column.belongsToTable()) {
      TABLE_HELPER.assertDoesNotContainColumnWithGivenName(column.getParentTable(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }

  //method
  public void assertCanSetParameterizedPropertyTypeToColumn(
    final Column column,
    final IParameterizedPropertyType parameterizedPropertyType) {

    COLUMN_HELPER.assertIsOpen(column);
    column.assertIsEmpty();

    if (PARAMETERIZED_PROPERTY_TYPE_HELPER.isABaseReferenceType(parameterizedPropertyType)
    && COLUMN_HELPER.belongsToDatabase(column)) {

      final var baseParameterizedReferenceType = (BaseParameterizedReferenceType) parameterizedPropertyType;
      final var referencedTable = baseParameterizedReferenceType.getReferencedTable();

      DATABASE_HELPER.assertContainsGivenTable(COLUMN_HELPER.getParentDatabase(column), referencedTable);
    }

    if (!PARAMETERIZED_PROPERTY_TYPE_HELPER.isABaseReferenceType(parameterizedPropertyType)) {
      column.assertIsNotBackReferenced();
    }

    if (PARAMETERIZED_PROPERTY_TYPE_HELPER.isABaseBackReferenceType(parameterizedPropertyType)
    && COLUMN_HELPER.belongsToDatabase(column)) {

      final var baseParameterizedBackReferenceType = (BaseParameterizedBackReferenceType) parameterizedPropertyType;
      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_HELPER.assertContainsTableWithGivenColumn(
        COLUMN_HELPER.getParentDatabase(column),
        backReferencedColumn);
    }
  }

  //method
  public void assertCanSetParentTableToColumn(final Column column, final Table parentTable) {

    COLUMN_HELPER.assertIsOpen(column);
    COLUMN_HELPER.assertDoesNotBelongToTable(column);

    TABLE_HELPER.assertDoesNotContainGivenColumn(parentTable, column);
  }
}
