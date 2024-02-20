//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjectvalidator.DatabaseObjectValidator;
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
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  //constant
  private static final ITableTool TABLE_TOOL = new TableTool();

  //constant
  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  //constant
  private static final IParameterizedPropertyTypeTool PARAMETERIZED_PROPERTY_TYPE_TOOL = //
  new ParameterizedPropertyTypeTool();

  //method
  public void assertCanDeleteColumn(final Column column) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNotDeleted(column);
    column.assertIsNotBackReferenced();
  }

  //method
  public void assertCanSetNameToColumn(final Column column, final String name) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);

    if (column.belongsToTable()) {
      TABLE_TOOL.assertDoesNotContainColumnWithGivenName(column.getParentTable(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }

  //method
  public void assertCanSetParameterizedPropertyTypeToColumn(
    final Column column,
    final IParameterizedPropertyType parameterizedPropertyType) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    column.assertIsEmpty();

    if (PARAMETERIZED_PROPERTY_TYPE_TOOL.isABaseReferenceType(parameterizedPropertyType)
    && COLUMN_TOOL.belongsToDatabase(column)) {

      final var baseParameterizedReferenceType = (BaseParameterizedReferenceType) parameterizedPropertyType;
      final var referencedTable = baseParameterizedReferenceType.getReferencedTable();

      DATABASE_TOOL.assertContainsGivenTable(COLUMN_TOOL.getParentDatabase(column), referencedTable);
    }

    if (!PARAMETERIZED_PROPERTY_TYPE_TOOL.isABaseReferenceType(parameterizedPropertyType)) {
      column.assertIsNotBackReferenced();
    }

    if (PARAMETERIZED_PROPERTY_TYPE_TOOL.isABaseBackReferenceType(parameterizedPropertyType)
    && COLUMN_TOOL.belongsToDatabase(column)) {

      final var baseParameterizedBackReferenceType = (BaseParameterizedBackReferenceType) parameterizedPropertyType;
      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_TOOL.assertContainsTableWithGivenColumn(
        COLUMN_TOOL.getParentDatabase(column),
        backReferencedColumn);
    }
  }

  //method
  public void assertCanSetParentTableToColumn(final Column column, final Table parentTable) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    COLUMN_TOOL.assertDoesNotBelongToTable(column);

    TABLE_TOOL.assertDoesNotContainGivenColumn(parentTable, column);
  }
}
