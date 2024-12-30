package ch.nolix.system.objectschema.schematool;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

public final class TableTool extends DatabaseObjectExaminer implements ITableTool {

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

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
    && table.getStoredParentDatabase().getStoredTables()
      .containsAny(t -> containsColumnThatReferencesGivenTable(t, table));
  }

  private IContainer<IColumn> getStoredBackReferencingColumnsWhenBelongsToDatabase(
    final ITable table) {

    final var columns = table.getStoredParentDatabase().getStoredTables().toMultiple(ITable::getStoredColumns);

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
      .getStoredParentDatabase()
      .getStoredTables()
      .toMultiple(ITable::getStoredColumns)
      .getStoredSelected(c -> COLUMN_TOOL.referencesGivenTable(c, table));
  }

  private IContainer<IColumn> getStoredReferencingColumnsWhenDoesNotBelongToDatabase(
    final ITable table) {
    return table.getStoredColumns().getStoredSelected(c -> COLUMN_TOOL.referencesGivenTable(c, table));
  }
}
