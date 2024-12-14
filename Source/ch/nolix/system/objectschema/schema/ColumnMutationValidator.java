package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.parameterizedfieldtype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.system.objectschema.schematool.ParameterizedFieldTypeTool;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedFieldTypeTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

final class ColumnMutationValidator {

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final ITableTool TABLE_TOOL = new TableTool();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  private static final IParameterizedFieldTypeTool PARAMETERIZED_FIELD_TYPE_TOOL = //
  new ParameterizedFieldTypeTool();

  public void assertCanDeleteColumn(final Column column) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNotDeleted(column);
    column.assertIsNotBackReferenced();
  }

  public void assertCanSetNameToColumn(final Column column, final String name) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);

    if (column.belongsToTable()) {
      TABLE_TOOL.assertDoesNotContainColumnWithGivenName(column.getStoredParentTable(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }

  public void assertCanSetParameterizedFieldTypeToColumn(
    final Column column,
    final IContentModel contentModel) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    column.assertIsEmpty();

    if (PARAMETERIZED_FIELD_TYPE_TOOL.isABaseReferenceType(contentModel)
    && COLUMN_TOOL.belongsToDatabase(column)) {

      final var baseParameterizedReferenceType = (BaseParameterizedReferenceType) contentModel;
      final var referencedTable = baseParameterizedReferenceType.getReferencedTable();

      DATABASE_TOOL.assertContainsGivenTable(COLUMN_TOOL.getParentDatabase(column), referencedTable);
    }

    if (!PARAMETERIZED_FIELD_TYPE_TOOL.isABaseReferenceType(contentModel)) {
      column.assertIsNotBackReferenced();
    }

    if (PARAMETERIZED_FIELD_TYPE_TOOL.isABaseBackReferenceType(contentModel)
    && COLUMN_TOOL.belongsToDatabase(column)) {

      final var baseParameterizedBackReferenceType = (BaseParameterizedBackReferenceType) contentModel;
      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_TOOL.assertContainsTableWithGivenColumn(
        COLUMN_TOOL.getParentDatabase(column),
        backReferencedColumn);
    }
  }

  public void assertCanSetParentTableToColumn(final Column column, final Table parentTable) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    COLUMN_TOOL.assertDoesNotBelongToTable(column);

    TABLE_TOOL.assertDoesNotContainGivenColumn(parentTable, column);
  }
}
