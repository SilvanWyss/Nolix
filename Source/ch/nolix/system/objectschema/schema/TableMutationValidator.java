package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.contentmodel.AbstractBackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.AbstractReferenceModel;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.system.objectschema.schemavalidator.DatabaseValidator;
import ch.nolix.systemapi.databaseobjectapi.databasevalidatorapi.IDatabaseObjectValidator;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;
import ch.nolix.systemapi.objectschemaapi.schemavalidatorapi.IDatabaseValidator;

final class TableMutationValidator {

  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final ITableTool TABLE_TOOL = new TableTool();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  public void assertCanAddColumnToTable(final Table table, final Column column) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    TABLE_TOOL.assertDoesNotContainColumnWithGivenName(table, column.getName());

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNew(column);

    if (COLUMN_TOOL.isAReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedReferenceType = (AbstractReferenceModel) column.getContentModel();
      final var referencedTables = baseParameterizedReferenceType.getReferencedTables();

      DATABASE_VALIDATOR.assertContainsTables(table.getStoredParentDatabase(), referencedTables);
    }

    if (COLUMN_TOOL.isABackReferenceColumn(column) && table.belongsToDatabase()) {

      final var baseParameterizedBackReferenceType = (AbstractBackReferenceModel) column
        .getContentModel();

      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_TOOL.assertContainsTableWithGivenColumn(table.getStoredParentDatabase(), backReferencedColumn);
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
      DATABASE_TOOL.assertDoesNotContainTableWithGivenName(table.getStoredParentDatabase(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }
}
