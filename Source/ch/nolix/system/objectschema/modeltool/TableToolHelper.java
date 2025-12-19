package ch.nolix.system.objectschema.modeltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;

/**
 * @author Silvan Wyss
 */
public final class TableToolHelper {
  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  private TableToolHelper() {
  }

  public static IContainer<? extends IColumn> getStoredBackReferencingColumnsWhenBelongsToDatabase(
    final ITable table) {
    final var columns = table.getStoredParentDatabase().getStoredTables().toMultiples(ITable::getStoredColumns);

    return //
    table
      .getStoredColumns()
      .getStoredSelected(c -> columns.containsAny(c2 -> COLUMN_TOOL.referencesBackGivenColumn(c, c2)));
  }

  public static IContainer<? extends IColumn> getStoredBackReferencingColumnsWhenDoesNotBelongToDatabase(
    final ITable table) {
    final var columns = table.getStoredColumns();

    return columns.getStoredSelected(c -> columns.containsAny(c2 -> COLUMN_TOOL.referencesBackGivenColumn(c, c2)));
  }

  public static IContainer<? extends IColumn> getStoredReferencingColumnsWhenBelongsToDatabase(final ITable table) {
    return //
    table
      .getStoredParentDatabase()
      .getStoredTables()
      .toMultiples(ITable::getStoredColumns)
      .getStoredSelected(c -> COLUMN_TOOL.referencesGivenTable(c, table));
  }

  public static IContainer<? extends IColumn> getStoredReferencingColumnsWhenDoesNotBelongToDatabase(
    final ITable table) {
    return table.getStoredColumns().getStoredSelected(c -> COLUMN_TOOL.referencesGivenTable(c, table));
  }
}
