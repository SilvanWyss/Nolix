package ch.nolix.systemapi.rawschemaapi.databaseinitializerapi;

import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IDatabaseInitializer {

  /**
   * @return the state of the database.
   */
  DatabaseState getDatabaseState();

  /**
   * Initializes the database if the database is not initialized.
   * 
   * @throws RuntimeException if the database is either not empty or not
   *                          initialized.
   */
  void initializeDatabaseIfNotInitialized();
}
