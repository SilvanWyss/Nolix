/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.IDatabaseExaminer;

/**
 * @author Silvan Wyss
 */
public final class DatabaseExaminer implements IDatabaseExaminer {
  private static final TableExaminer TABLE_EXAMINER = new TableExaminer();

  private static final ColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  @Override
  public boolean allBaseBackReferencesAreValid(final IDatabase database) {
    final var tables = database.getStoredTables();
    final var columns = tables.toMultiples(ITable::getStoredColumns);

    final var baseBackReferenceColumnsView = //
    columns.getViewOfStoredSelected(COLUMN_EXAMINER::isBaseBackReferenceColumn);

    return baseBackReferenceColumnsView.containsMatchingOnly(COLUMN_EXAMINER::isValidBaseBackReferenceColumn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetName(final String name) {
    return //
    name != null
    && !name.isBlank();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsBackReferencededColumnByColumn(
    final IDatabase database,
    final IColumn column) {
    // This part is not mandatory, but provides a better performance.
    if (!COLUMN_EXAMINER.isBaseBackReferenceColumn(column)) {
      return false;
    }

    return //
    database != null
    && database.getStoredTables().containsMatching(
      t -> TABLE_EXAMINER.containsColumnThatIsBackReferencedByColumn(t, column));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsTable(final IDatabase database, ITable table) {
    return //
    database != null
    && database.getStoredTables().contains(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsTableReferencedByColumn(final IDatabase database, final IColumn column) {
    // This part is not mandatory, but provides a better performance.
    if (!COLUMN_EXAMINER.isBaseReferenceColumn(column)) {
      return false;
    }

    return //
    database != null
    && database.getStoredTables().containsMatching(column::referencesTable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsTableWithColumn(final IDatabase database, final IColumn column) {
    return //
    database != null
    && database.getStoredTables().containsMatching(t -> TABLE_EXAMINER.containsColumn(t, column));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsTableWithName(final IDatabase database, final String name) {
    return //
    database != null
    && database.getStoredTables().containsMatching(t -> t.hasName(name));
  }

}
