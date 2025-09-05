package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.modelexaminer.IDatabaseExaminer;
import ch.nolix.systemapi.objectdata.modelexaminer.ITableExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class DatabaseExaminer extends DatabaseObjectExaminer implements IDatabaseExaminer {
  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final IDatabase database) {
    return database.getStoredTables().containsOnly(TABLE_EXAMINER::allNewAndEditedMandatoryFieldsAreSet);
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
