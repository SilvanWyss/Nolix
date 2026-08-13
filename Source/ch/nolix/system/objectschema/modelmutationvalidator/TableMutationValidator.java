/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelmutationvalidator;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.system.database.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.modelexaminer.ColumnExaminer;
import ch.nolix.system.objectschema.modelvalidator.DatabaseValidator;
import ch.nolix.system.objectschema.modelvalidator.TableValidator;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationvalidator.ITableMutationValidator;

/**
 * @author Silvan Wyss
 */
public final class TableMutationValidator implements ITableMutationValidator {
  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final TableValidator TABLE_VALIDATOR = new TableValidator();

  private static final ColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanAddColumnToTable(final ITable table, final IColumn column) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    TABLE_VALIDATOR.assertDoesNotContainColumnWithName(table, column.getName());

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNew(column);

    if (COLUMN_EXAMINER.isBaseReferenceColumn(column) && table.belongsToDatabase()) {
      final var referencedTables = column.getStoredReferenceableTables();
      final var database = table.getStoredParentDatabase();

      referencedTables.forEach(t -> DATABASE_VALIDATOR.assertContainsTable(database, t));
    }

    if (COLUMN_EXAMINER.isBaseBackReferenceColumn(column) && table.belongsToDatabase()) {
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

    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
  }
}
