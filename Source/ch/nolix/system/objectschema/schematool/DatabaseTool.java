package ch.nolix.system.objectschema.schematool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectschema.modelexaminer.DatabaseExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IDatabaseExaminer;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

public final class DatabaseTool extends DatabaseObjectExaminer implements IDatabaseTool {

  private static final IDatabaseExaminer DATABASE_EXAMINER = new DatabaseExaminer();

  private static final ITableTool TABLE_TOOL = new TableTool();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  @Override
  public boolean allBackReferencesAreValid(final IDatabase database) {
    return getStoredAllBackReferenceColumns(database).containsOnly(COLUMN_TOOL::isAValidBackReferenceColumn);
  }

  @Override
  public void assertAllBackReferencesAreValid(final IDatabase database) {
    if (!allBackReferencesAreValid(database)) {
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

  @Override
  public void deleteTableWithGivenName(final IDatabase database, final String name) {
    getStoredTableWithGivenName(database, name).delete();
  }

  @Override
  public IContainer<? extends IColumn> getStoredAllBackReferenceColumns(final IDatabase database) {
    return database.getStoredTables().toMultiples(TABLE_TOOL::getStoredBackReferenceColumns);
  }

  @Override
  public ITable getStoredTableWithGivenName(final IDatabase database, final String name) {
    return database.getStoredTables().getStoredFirst(t -> t.hasName(name));
  }

  @Override
  public int getTableCount(final IDatabase database) {
    return database.getStoredTables().getCount();
  }
}
