/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelmutationexaminer;

import ch.nolix.system.objectschema.modelexaminer.DatabaseExaminer;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationexaminer.IDatabaseMutationExaminer;

/**
 * @author Silvan Wyss
 */
public final class DatabaseMutationExaminer implements IDatabaseMutationExaminer {
  private static final DatabaseExaminer DATABASE_EXAMINER = new DatabaseExaminer();

  private static final TableMutationExaminer TABLE_MUTATION_EXAMINER = new TableMutationExaminer();

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
    && DatabaseMutationExaminerHelper.canAddGivenTableBecauseOfColumns(database, table);
  }
}
