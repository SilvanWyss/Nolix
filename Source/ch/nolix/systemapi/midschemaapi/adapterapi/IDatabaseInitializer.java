package ch.nolix.systemapi.midschemaapi.adapterapi;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IDatabaseInitializer {

  /**
   * Initializes the database if the database is not initialized.
   * 
   * @throws RuntimeException if the database is not empty or not properly (!)
   *                          initialized.
   */
  void initializeDatabaseIfNotInitialized();
}
