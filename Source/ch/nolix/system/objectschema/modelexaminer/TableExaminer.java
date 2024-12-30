package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.ITableExaminer;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class TableExaminer implements ITableExaminer {

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsColumn(final ITable table, final IColumn column) {
    return //
    table != null
    && table.getStoredColumns().contains(column);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsColumnThatIsBackReferencedByColumn(final ITable table, final IColumn column) {

    return //
    table != null

    //This check is  not necessary, but provides a better performance for some cases.
    && COLUMN_TOOL.isABackReferenceColumn(column)

    && table.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, column));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsColumnThatReferencesBackColumn(final ITable table, final IColumn column) {

    return //
    table != null

    //This check is  not necessary, but provides a better performance for some cases.
    && COLUMN_TOOL.isAReferenceColumn(column)

    && table.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, column));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsColumnThatReferencesTable(
    final ITable table,
    final ITable otherTable) {
    return //
    table != null
    && table.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesGivenTable(c, otherTable));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsColumnWithName(final ITable table, final String name) {
    return //
    table != null
    && table.getStoredColumns().containsAny(c -> c.hasName(name));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferenced(final ITable table) {
    return //
    table != null
    && table.belongsToDatabase()
    && table.getStoredParentDatabase().getStoredTables().containsAny(t -> containsColumnThatReferencesTable(t, table));
  }
}
