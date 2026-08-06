/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelmutationexaminer;

import ch.nolix.system.objectschema.modelexaminer.DatabaseExaminer;
import ch.nolix.system.objectschema.modelsearcher.ColumnSearcher;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationexaminer.IDatabaseMutationExaminer;

/**
 * @author Silvan Wyss
 */
public final class DatabaseMutationExaminer implements IDatabaseMutationExaminer {
  private static final DatabaseExaminer DATABASE_EXAMINER = new DatabaseExaminer();

  private static final TableMutationExaminer TABLE_MUTATION_EXAMINER = new TableMutationExaminer();

  private static final ColumnSearcher COLUMN_SEARCHER = new ColumnSearcher();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canAddTable(final IDatabase database) {
    return //
    database != null
    && database.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canAddTable(final IDatabase database, final ITable table) {
    return //
    canAddTable(database)
    && TABLE_MUTATION_EXAMINER.canBeAddedToDatabase(table)
    && !DATABASE_EXAMINER.containsTableWithName(database, table.getName())
    && canAddGivenTableBecauseOfColumns(database, table);
  }

  private boolean canAddGivenTableBecauseOfColumns(final IDatabase database, final ITable table) {
    return table.getStoredColumns().containsMatchingOnly(c -> canAddGivenTableBecauseOfGivenColumn(database, table, c));
  }

  private boolean canAddGivenTableBecauseOfGivenColumn(
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

  private boolean canAddGivenTableBecauseOfGivenReferenceColumn(
    final IDatabase database,
    final ITable table,
    final IColumn referenceColumn) {
    return //
    DATABASE_EXAMINER.containsTableReferencedByColumn(database, referenceColumn)
    || referenceColumn.referencesTable(table);
  }
}
