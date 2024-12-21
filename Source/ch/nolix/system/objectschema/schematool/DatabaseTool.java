package ch.nolix.system.objectschema.schematool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

public final class DatabaseTool extends DatabaseObjectExaminer implements IDatabaseTool {

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
    if (!canAddGivenTable(database, table)) {

      if (table == null) {
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.TABLE);
      }

      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        database,
        "cannot add the given table '" + table.getName() + "'");
    }
  }

  @Override
  public void assertCanSetGivenNameToDatabase(final String name) {
    if (!canSetGivenNameToDatabase(name)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.NAME,
        name,
        "cannot be set to database");
    }
  }

  @Override
  public void assertContainsTableReferencedByGivenColumn(
    final IDatabase database,
    final IColumn column) {
    if (!containsTableReferencedByGivenColumn(database, column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        database,
        "does not contain a table that is referenced by the column " + column.getNameInQuotes());
    }
  }

  @Override
  public void assertContainsTableWithColumnBackReferencedByGivenColumn(
    final IDatabase database,
    final IColumn column) {
    if (!containsTableWithColumnBackReferencedByGivenColumn(database, column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "does not contain a table with a column that references back the column " + column.getName());
    }

  }

  @Override
  public void assertContainsTableWithGivenColumn(final IDatabase database, final IColumn column) {
    if (!containsTableWithGivenColumn(database, column)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(this, column);
    }
  }

  @Override
  public void assertDoesNotContainTableWithGivenName(final IDatabase database, final String name) {
    if (containsTableWithGivenName(database, name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this,
        "contains a table with the name '" + name + "'");
    }
  }

  @Override
  public boolean canAddGivenTable(final IDatabase database, final ITable table) {
    return canAddTable(database)
    && TABLE_TOOL.canBeAddedToDatabase(table)
    && !containsTableWithGivenName(database, table.getName())
    && canAddGivenTableBecauseOfColumns(database, table);
  }

  @Override
  public boolean canAddTable(final IDatabase database) {
    return database != null
    && database.isOpen();
  }

  @Override
  public boolean canSetGivenNameToDatabase(final String name) {
    return !name.isBlank();
  }

  @Override
  public boolean containsGivenTable(final IDatabase database, ITable table) {
    return database.getStoredTables().contains(table);
  }

  @Override
  public boolean containsTableReferencedByGivenColumn(
    final IDatabase database,
    final IColumn column) {

    //This check is theoretically not necessary, but provides a better performance
    //for some cases.
    if (!COLUMN_TOOL.isAReferenceColumn(column)) {
      return false;
    }

    return database.getStoredTables().containsAny(t -> COLUMN_TOOL.referencesGivenTable(column, t));
  }

  @Override
  public boolean containsTableWithColumnBackReferencedByGivenColumn(
    final IDatabase database,
    final IColumn column) {

    //This check is theoretically not necessary, but provides a better performance
    //for some cases.
    if (!COLUMN_TOOL.isABackReferenceColumn(column)) {
      return false;
    }

    return database.getStoredTables()
      .containsAny(t -> TABLE_TOOL.containsColumnBackReferencedByGivenColumn(t, column));
  }

  @Override
  public boolean containsTableWithGivenColumn(final IDatabase database, final IColumn column) {
    return database.getStoredTables().containsAny(t -> TABLE_TOOL.containsGivenColumn(t, column));
  }

  @Override
  public boolean containsTableWithGivenName(final IDatabase database, final String name) {
    return database.getStoredTables().containsAny(t -> t.hasName(name));
  }

  @Override
  public void deleteTableWithGivenName(final IDatabase database, final String name) {
    getStoredTableWithGivenName(database, name).delete();
  }

  @Override
  public IContainer<IColumn> getStoredAllBackReferenceColumns(final IDatabase database) {
    return database.getStoredTables().toMultiple(TABLE_TOOL::getStoredBackReferenceColumns);
  }

  @Override
  public ITable getStoredTableWithGivenName(final IDatabase database, final String name) {
    return database.getStoredTables().getStoredFirst(t -> t.hasName(name));
  }

  @Override
  public int getTableCount(final IDatabase database) {
    return database.getStoredTables().getCount();
  }

  private boolean canAddGivenTableBecauseOfColumns(final IDatabase database, final ITable table) {
    return table.getStoredColumns().containsOnly(c -> canAddGivenTableBecauseOfGivenColumn(database, table, c));
  }

  private boolean canAddGivenTableBecauseOfGivenColumn(
    final IDatabase database,
    final ITable table,
    final IColumn column) {
    return switch (COLUMN_TOOL.getBaseFieldType(column)) {
      case BASE_VALUE ->
        true;
      case BASE_REFERENCE ->
        canAddGivenTableBecauseOfGivenReferenceColumn(database, table, column);
      case BASE_BACK_REFERENCE ->
        true;
      default ->
        true;
    };
  }

  private boolean canAddGivenTableBecauseOfGivenReferenceColumn(
    final IDatabase database,
    final ITable table,
    final IColumn referenceColumn) {
    return containsTableReferencedByGivenColumn(database, referenceColumn)
    || COLUMN_TOOL.referencesGivenTable(referenceColumn, table);
  }
}
