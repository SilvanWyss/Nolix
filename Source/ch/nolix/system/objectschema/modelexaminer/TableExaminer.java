/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.system.objectschema.modeltool.ColumnTool;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.ITableExaminer;

/**
 * @author Silvan Wyss
 */
public final class TableExaminer implements ITableExaminer {
  private static final ColumnTool COLUMN_TOOL = new ColumnTool();

  private static final ColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

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

    // This part is not mandatory, but provides a better performance.
    && COLUMN_EXAMINER.isBaseBackReferenceColumn(column)

    && table.getStoredColumns().containsMatching(c -> COLUMN_TOOL.referencesBackGivenColumn(c, column));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsColumnThatReferencesBackColumn(final ITable table, final IColumn column) {
    return //
    table != null

    // This part is not mandatory, but provides a better performance.
    && COLUMN_EXAMINER.isBaseReferenceColumn(column)

    && table.getStoredColumns().containsMatching(c -> COLUMN_TOOL.referencesBackGivenColumn(c, column));
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
    && table.getStoredColumns().containsMatching(c -> c.referencesTable(otherTable));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsColumnWithName(final ITable table, final String name) {
    return //
    table != null
    && table.getStoredColumns().containsMatching(c -> c.hasName(name));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferenced(final ITable table) {
    return //
    table != null
    && table.belongsToDatabase()
    && table.getStoredParentDatabase().getStoredTables().containsMatching(
      t -> containsColumnThatReferencesTable(t, table));
  }
}
