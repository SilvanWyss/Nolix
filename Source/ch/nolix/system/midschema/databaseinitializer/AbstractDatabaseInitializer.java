package ch.nolix.system.midschema.databaseinitializer;

import ch.nolix.core.errorcontrol.generalexception.GeneralException;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.midschema.databaseinitializer.IDatabaseInitializer;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public abstract class AbstractDatabaseInitializer implements IDatabaseInitializer {

  /**
   * {@inheritDoc}
   */
  @Override
  public final void initializeDatabaseIfNotInitialized() {
    switch (getDatabaseState()) {
      case UNINITIALIZED:
        initializeDatabase();
        break;
      case INITIALIZED:
        //Does nothing.
        break;
      case INVALID:
        throw GeneralException.withErrorMessage("The database has a schema that does not suit.");
    }
  }

  /**
   * Initializes the database with the given initialSchemaTimeStamp.
   * 
   * @param initialSchemaTimeStamp
   * @throws RuntimeException if the given initialSchemaTimeStamp is null.
   */
  protected abstract void initializeDatabaseWithInitialSchemaTimestamp(ITime initialSchemaTimeStamp);

  /**
   * Initializes the database.
   */
  private void initializeDatabase() {

    final var initialSchemaTimestamp = Time.ofNow();

    initializeDatabaseWithInitialSchemaTimestamp(initialSchemaTimestamp);
  }
}
