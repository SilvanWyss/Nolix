//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.ParameterizedPropertyTypeHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IParameterizedPropertyTypeHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class ColumnMutationValidator {

  // constant
  private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();

  // constant
  private static final ITableHelper TABLE_HELPER = new TableHelper();

  // constant
  private static final IColumnHelper COLUMN_HELPER = new ColumnHelper();

  // constant
  private static final IParameterizedPropertyTypeHelper PARAMETERIZED_PROPERTY_TYPE_HELPER = //
      new ParameterizedPropertyTypeHelper();

  // method
  public void assertCanDeleteColumn(final Column column) {
    COLUMN_HELPER.assertIsOpen(column);
    COLUMN_HELPER.assertIsNotDeleted(column);
    column.assertIsNotBackReferenced();
  }

  // method
  public void assertCanSetNameToColumn(final Column column, final String name) {

    COLUMN_HELPER.assertIsOpen(column);

    if (column.belongsToTable()) {
      TABLE_HELPER.assertDoesNotContainColumnWithGivenName(column.getParentTable(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
  }

  // method
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

  // method
  public void assertCanSetParentTableToColumn(final Column column, final Table parentTable) {

    COLUMN_HELPER.assertIsOpen(column);
    COLUMN_HELPER.assertDoesNotBelongToTable(column);

    TABLE_HELPER.assertDoesNotContainGivenColumn(parentTable, column);
  }
}
