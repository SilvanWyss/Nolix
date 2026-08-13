/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.system.database.databaseobjectexaminer.AbstractDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.modelexaminer.IDatabaseExaminer;

/**
 * @author Silvan Wyss
 */
public final class DatabaseExaminer extends AbstractDatabaseObjectExaminer<IDatabase> implements IDatabaseExaminer {
  private static final TableExaminer TABLE_EXAMINER = new TableExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final IDatabase database) {
    return database.getStoredTables().containsMatchingOnly(TABLE_EXAMINER::allNewAndEditedMandatoryFieldsAreSet);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSaveChanges(final IDatabase database) {
    return database.isOpen()
    && database.isConnectedWithRealDatabase()
    && allNewAndEditedMandatoryFieldsAreSet(database);
  }
}
