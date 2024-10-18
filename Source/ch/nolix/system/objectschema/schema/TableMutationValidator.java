package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.parameterizedfieldtype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

final class TableMutationValidator {

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final ITableTool TABLE_TOOL = new TableTool();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  public void assertCanAddColumnToTable(final Table table, final Column column) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    TABLE_TOOL.assertDoesNotContainColumnWithGivenName(table, column.getName());

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNew(column);

    if (COLUMN_TOOL.isAReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedReferenceType = (BaseParameterizedReferenceType) column.getParameterizedFieldType();
      final var referencedTable = baseParameterizedReferenceType.getReferencedTable();

      DATABASE_TOOL.assertContainsGivenTable(table.getParentDatabase(), referencedTable);
    }

    if (COLUMN_TOOL.isABackReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedBackReferenceType = (BaseParameterizedBackReferenceType) column
        .getParameterizedFieldType();

      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_TOOL.assertContainsTableWithGivenColumn(table.getParentDatabase(), backReferencedColumn);
    }
  }

  public void assertCanDeleteTable(final Table table) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    DATABASE_OBJECT_VALIDATOR.assertIsNotNew(table);
    DATABASE_OBJECT_VALIDATOR.assertIsNotDeleted(table);
    TABLE_TOOL.assertIsNotReferenced(table);
  }

  public void assertCanSetNameToTable(final Table table, final String name) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);

    if (table.belongsToDatabase()) {
      DATABASE_TOOL.assertDoesNotContainTableWithGivenName(table.getParentDatabase(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }
}
