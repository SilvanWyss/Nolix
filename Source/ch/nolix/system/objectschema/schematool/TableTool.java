package ch.nolix.system.objectschema.schematool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

public final class TableTool extends DatabaseObjectTool implements ITableTool {

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  @Override
  public void assertContainsGivenColumn(final ITable table, final IColumn column) {
    if (!containsGivenColumn(table, column)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(table, column);
    }
  }

  @Override
  public void assertDoesNotBelongToDatabase(final ITable table) {
    if (table.belongsToDatabase()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(table, table.getParentDatabase());
    }
  }

  @Override
  public void assertDoesNotContainGivenColumn(final ITable table, final IColumn column) {
    if (containsGivenColumn(table, column)) {
      throw ArgumentContainsElementException.forArgumentAndElement(table, column);
    }
  }

  @Override
  public void assertDoesNotContainColumnWithGivenName(final ITable table, final String name) {
    if (containsColumnWithGivenName(table, name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        table,
        "contains already a column with the given name '" + name + "'");
    }
  }

  @Override
  public void assertIsNotReferenced(final ITable table) {
    if (isReferenced(table)) {
      throw ReferencedArgumentException.forArgument(table);
    }
  }

  @Override
  public boolean canBeAddedToDatabase(final ITable table) {
    return table != null
    && table.isOpen()
    && !table.belongsToDatabase();
  }

  @Override
  public boolean containsGivenColumn(final ITable table, final IColumn column) {
    return table.getStoredColumns().contains(column);
  }

  @Override
  public boolean containsColumnBackReferencedByGivenColumn(final ITable table, final IColumn column) {

    //This check is theoretically not necessary, but provides a better performance
    //for some cases.
    if (!COLUMN_TOOL.isABackReferenceColumn(column)) {
      return false;
    }

    return table.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, column));
  }

  @Override
  public boolean containsColumnThatReferencesBackGivenColumn(final ITable table, final IColumn column) {

    //This check is theoretically not necessary, but provides a better performance
    //for some cases.
    if (!COLUMN_TOOL.isAReferenceColumn(column)) {
      return false;
    }

    return table.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, column));
  }

  @Override
  public boolean containsColumnThatReferencesGivenTable(
    final ITable table,
    final ITable probableReferencedTable) {
    return table.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesGivenTable(c, table));
  }

  @Override
  public boolean containsColumnWithGivenName(final ITable table, final String name) {
    return table.getStoredColumns().containsAny(c -> c.hasName(name));
  }

  @Override
  public int getColumnCount(final ITable table) {
    return table.getStoredColumns().getCount();
  }

  @Override
  public IContainer<IColumn> getStoredBackReferenceColumns(final ITable table) {
    return table.getStoredColumns().getStoredSelected(COLUMN_TOOL::isABackReferenceColumn);
  }

  @Override
  public IContainer<IColumn> getStoredBackReferencingColumns(final ITable table) {

    if (!table.belongsToDatabase()) {
      return getStoredBackReferencingColumnsWhenDoesNotBelongToDatabase(table);
    }

    return getStoredBackReferencingColumnsWhenBelongsToDatabase(table);
  }

  @Override
  public IContainer<IColumn> getStoredReferencingColumns(final ITable table) {

    if (!table.belongsToDatabase()) {
      return getStoredReferencingColumnsWhenDoesNotBelongToDatabase(table);
    }

    return getStoredReferencingColumnsWhenBelongsToDatabase(table);
  }

  @Override
  public boolean isReferenced(final ITable table) {
    return table.belongsToDatabase()
    && table.getParentDatabase().getStoredTables()
      .containsAny(t -> containsColumnThatReferencesGivenTable(t, table));
  }

  private IContainer<IColumn> getStoredBackReferencingColumnsWhenBelongsToDatabase(
    final ITable table) {

    final var columns = table.getParentDatabase().getStoredTables().toFromGroups(ITable::getStoredColumns);

    return table
      .getStoredColumns()
      .getStoredSelected(c -> columns.containsAny(c2 -> COLUMN_TOOL.referencesBackGivenColumn(c, c2)));
  }

  private IContainer<IColumn> getStoredBackReferencingColumnsWhenDoesNotBelongToDatabase(
    final ITable table) {

    final var columns = table.getStoredColumns();

    return columns.getStoredSelected(c -> columns.containsAny(c2 -> COLUMN_TOOL.referencesBackGivenColumn(c, c2)));
  }

  private IContainer<IColumn> getStoredReferencingColumnsWhenBelongsToDatabase(final ITable table) {
    return table
      .getParentDatabase()
      .getStoredTables()
      .toFromGroups(ITable::getStoredColumns)
      .getStoredSelected(c -> COLUMN_TOOL.referencesGivenTable(c, table));
  }

  private IContainer<IColumn> getStoredReferencingColumnsWhenDoesNotBelongToDatabase(
    final ITable table) {
    return table.getStoredColumns().getStoredSelected(c -> COLUMN_TOOL.referencesGivenTable(c, table));
  }
}
