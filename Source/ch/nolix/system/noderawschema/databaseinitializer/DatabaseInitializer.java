package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;
import ch.nolix.systemapi.rawschemaapi.databaseinitializerapi.IDatabaseInitializer;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class DatabaseInitializer implements IDatabaseInitializer {

  private final IMutableNode<?> nodeDatabase;

  /**
   * Creates a new {@link DatabaseInitializer} for the given nodeDatabase.
   * 
   * @param nodeDatabase
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  private DatabaseInitializer(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  /**
   * 
   * @param nodeDatabase
   * @return a new {@link DatabaseInitializer} for the given nodeDatabase.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  public static DatabaseInitializer forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DatabaseInitializer(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseState getDatabaseState() {
    //TODO: Implement
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDatabaseIfNotInitialized() {
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
   * Initializes the database for the case that the database is not initialized.
   */
  private void initializeDatabase() {
    //TODO: Implement
  }
}
