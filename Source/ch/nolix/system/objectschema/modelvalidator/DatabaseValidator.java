package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectschema.modelexaminer.DatabaseExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.IDatabaseExaminer;
import ch.nolix.systemapi.objectschema.modelvalidator.IDatabaseValidator;

public final class DatabaseValidator implements IDatabaseValidator {
  private static final IDatabaseExaminer DATABASE_EXAMINER = new DatabaseExaminer();

  @Override
  public void assertContainsTable(final IDatabase database, final ITable table) {
    if (!DATABASE_EXAMINER.containsTable(database, table)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(database, table);
    }
  }

  @Override
  public void assertContainsTables(final IDatabase database, final IContainer<ITable> tables) {
    for (final var t : tables) {
      assertContainsTable(database, t);
    }
  }

  @Override
  public void assertAllBackReferencesAreValid(final IDatabase database) {
    if (!DATABASE_EXAMINER.allBackReferencesAreValid(database)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(database, "contains invalid back references");
    }
  }

  @Override
  public void assertCanAddGivenTable(final IDatabase database, final ITable table) {
    if (!DATABASE_EXAMINER.canAddTable(database, table)) {
      if (table == null) {
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.TABLE);
      }

      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        database,
        "cannot add the given table '" + table.getName() + "'");
    }
  }

  @Override
  public void assertCanSetGivenNameToDatabase(final String name) {
    if (!DATABASE_EXAMINER.canSetName(name)) {
      throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        LowerCaseVariableCatalog.NAME,
        name,
        "cannot be set to database");
    }
  }

  @Override
  public void assertContainsTableReferencedByGivenColumn(
    final IDatabase database,
    final IColumn column) {
    if (!DATABASE_EXAMINER.containsTableReferencedByColumn(database, column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        database,
        "does not contain a table that is referenced by the column " + column.getNameInQuotes());
    }
  }

  @Override
  public void assertContainsTableWithColumnBackReferencedByGivenColumn(
    final IDatabase database,
    final IColumn column) {
    if (!DATABASE_EXAMINER.containsBackReferencededColumnByColumn(database, column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "does not contain a table with a column that references back the column " + column.getName());
    }

  }

  @Override
  public void assertContainsTableWithGivenColumn(final IDatabase database, final IColumn column) {
    if (!DATABASE_EXAMINER.containsTableWithColumn(database, column)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(this, column);
    }
  }

  @Override
  public void assertDoesNotContainTableWithGivenName(final IDatabase database, final String name) {
    if (DATABASE_EXAMINER.containsTableWithName(database, name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this,
        "contains a table with the name '" + name + "'");
    }
  }
}
