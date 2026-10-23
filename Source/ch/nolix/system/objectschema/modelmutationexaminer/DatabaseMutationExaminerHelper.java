/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelmutationexaminer;

import ch.nolix.system.objectschema.modelexaminer.DatabaseExaminer;
import ch.nolix.system.objectschema.modelsearcher.ColumnSearcher;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * Of the {@link DatabaseMutationExaminerHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class DatabaseMutationExaminerHelper {
  private static final DatabaseExaminer DATABASE_EXAMINER = new DatabaseExaminer();

  private static final ColumnSearcher COLUMN_SEARCHER = new ColumnSearcher();

  private DatabaseMutationExaminerHelper() {
  }

  /**
   * @param database
   * @param table
   * @return true if the given table can be added to the given database regarding
   *         the columns of the given table, false otherwise
   */
  public static boolean canAddGivenTableBecauseOfColumns(final IDatabase database, final ITable table) {
    return table.getStoredColumns().containsMatchingOnly(c -> canAddGivenTableBecauseOfGivenColumn(database, table, c));
  }

  /**
   * @param database
   * @param table
   * @param column
   * @return true if the given table can be added to the given database regarding
   *         the given column, false otherwise
   */
  public static boolean canAddGivenTableBecauseOfGivenColumn(
    final IDatabase database,
    final ITable table,
    final IColumn column) {
    final var baseFieldType = COLUMN_SEARCHER.getBaseFieldType(column);

    return //
    switch (baseFieldType) {
      case BASE_VALUE_FIELD ->
        true;
      case BASE_REFERENCE ->
        canAddGivenTableBecauseOfGivenReferenceColumn(database, table, column);
      case BASE_BACK_REFERENCE ->
        true;
      default ->
        true;
    };
  }

  /**
   * @param database
   * @param table
   * @param referenceColumn
   * @return true if the given table can be added to the given database regarding
   *         the given referenceColumn, false otherwise
   */
  public static boolean canAddGivenTableBecauseOfGivenReferenceColumn(
    final IDatabase database,
    final ITable table,
    final IColumn referenceColumn) {
    return //
    DATABASE_EXAMINER.containsTableReferencedByColumn(database, referenceColumn)
    || referenceColumn.referencesTable(table);
  }
}
