package ch.nolix.system.objectschema.modelmutationvalidator;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.modeltool.ColumnTool;
import ch.nolix.system.objectschema.modelvalidator.DatabaseValidator;
import ch.nolix.system.objectschema.modelvalidator.TableValidator;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationvalidator.ITableMutationValidator;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;
import ch.nolix.systemapi.objectschema.modelvalidator.IDatabaseValidator;
import ch.nolix.systemapi.objectschema.modelvalidator.ITableValidator;

/**
 * @author Silvan Wyss
 */
public final class TableMutationValidator implements ITableMutationValidator {
  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final ITableValidator TABLE_VALIDATOR = new TableValidator();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanAddColumnToTable(final ITable table, final IColumn column) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    TABLE_VALIDATOR.assertDoesNotContainColumnWithName(table, column.getName());

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNew(column);

    if (COLUMN_TOOL.isAReferenceColumn(column) && table.belongsToDatabase()) {
      final var referencedTables = column.getStoredReferenceableTables();

      final var database = table.getStoredParentDatabase();
      referencedTables.forEach(t -> DATABASE_VALIDATOR.assertContainsTable(database, t));
    }

    if (COLUMN_TOOL.isABackReferenceColumn(column) && table.belongsToDatabase()) {
      final var backReferenceableColumns = column.getStoredBackReferenceableColumns();
      final var database = table.getStoredParentDatabase();

      backReferenceableColumns.forEach(c -> DATABASE_VALIDATOR.assertContainsTableWithGivenColumn(database, c));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanDeleteTable(final ITable table) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    DATABASE_OBJECT_VALIDATOR.assertIsNotNew(table);
    DATABASE_OBJECT_VALIDATOR.assertIsNotDeleted(table);
    TABLE_VALIDATOR.assertIsNotReferenced(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanSetNameToTable(final ITable table, final String name) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);

    if (table.belongsToDatabase()) {
      DATABASE_VALIDATOR.assertDoesNotContainTableWithGivenName(table.getStoredParentDatabase(), name);
    }

    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
  }
}
