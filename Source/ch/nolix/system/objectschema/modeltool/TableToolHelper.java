/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modeltool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
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

  public static ExtendedIterable<? extends IColumn> getStoredBackReferencingColumnsWhenBelongsToDatabase(
    final ITable table) {
    final var columns = table.getStoredParentDatabase().getStoredTables().toMultiples(ITable::getStoredColumns);

    return //
    table
      .getStoredColumns()
      .getStoredSelected(c -> columns.containsMatching(c2 -> COLUMN_TOOL.referencesBackGivenColumn(c, c2)));
  }

  public static ExtendedIterable<? extends IColumn> getStoredBackReferencingColumnsWhenDoesNotBelongToDatabase(
    final ITable table) {
    final var columns = table.getStoredColumns();

    return columns.getStoredSelected(c -> columns.containsMatching(c2 -> COLUMN_TOOL.referencesBackGivenColumn(c, c2)));
  }

  public static ExtendedIterable<? extends IColumn> getStoredReferencingColumnsWhenBelongsToDatabase(
    final ITable table) {
    return //
    table
      .getStoredParentDatabase()
      .getStoredTables()
      .toMultiples(ITable::getStoredColumns)
      .getStoredSelected(c -> c.referencesTable(table));
  }

  public static ExtendedIterable<? extends IColumn> getStoredReferencingColumnsWhenDoesNotBelongToDatabase(
    final ITable table) {
    return table.getStoredColumns().getStoredSelected(c -> c.referencesTable(table));
  }
}
