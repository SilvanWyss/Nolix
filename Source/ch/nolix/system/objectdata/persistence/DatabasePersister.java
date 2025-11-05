package ch.nolix.system.objectdata.persistence;

import ch.nolix.system.objectdata.expectation.DatabaseExpectationAdder;
import ch.nolix.system.objectdata.modelvalidator.DatabaseValidator;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.expectation.IDatabaseExpectationAdder;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.modelvalidator.IDatabaseValidator;
import ch.nolix.systemapi.objectdata.perstistence.IDatabasePersister;
import ch.nolix.systemapi.objectdata.perstistence.IDatabaseSaver;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
 */
public final class DatabasePersister implements IDatabasePersister {
  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final IDatabaseSaver DATABASE_SAVER = new DatabaseSaver();

  private static final IDatabaseExpectationAdder DATABASE_EXPECTATION_ADDER = new DatabaseExpectationAdder();

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

    DATABASE_EXPECTATION_ADDER.addExpectationThatNewlyReferencedEntitiesExist(database, dataAndSchemaAdapter);

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
