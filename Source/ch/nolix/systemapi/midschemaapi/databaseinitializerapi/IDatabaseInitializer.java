package ch.nolix.systemapi.midschemaapi.databaseinitializerapi;

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
   * @throws RuntimeException if the database is not empty or not properly (!)
   *                          initialized.
   */
  void initializeDatabaseIfNotInitialized();
}
