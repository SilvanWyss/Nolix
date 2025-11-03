package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.system.objectdata.modelvalidator.DatabaseValidator;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IDatabasePersister;
import ch.nolix.systemapi.objectdata.changesetsaver.IDatabaseSaver;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.modelvalidator.IDatabaseValidator;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
 */
public final class DatabasePersister implements IDatabasePersister {
  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final IDatabaseSaver DATABASE_SAVER = new DatabaseSaver();

  private static final ChangeSetSaveValidator DATABASE_SAVE_VALIDATOR = new ChangeSetSaveValidator();

  /**
   * {@inheritDoc}
   */
  @Override
  public void persistDatabaseChanges(
    final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    DATABASE_VALIDATOR.assertCanSaveChanges(database);

    dataAndSchemaAdapter.expectSchemaTimestamp(database.getSchemaTimestamp());

    DATABASE_SAVER.saveDatabaseChanges(database, dataAndSchemaAdapter);

    DATABASE_SAVE_VALIDATOR.addExpectationToDatabaseThatNewlyReferencedEntitiesExist(database, dataAndSchemaAdapter);

    dataAndSchemaAdapter.saveChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void persistDatabaseChangesTransactional(
    final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    synchronized (DatabasePersister.class) {
      persistDatabaseChanges(database, dataAndSchemaAdapter);
    }
  }
}
