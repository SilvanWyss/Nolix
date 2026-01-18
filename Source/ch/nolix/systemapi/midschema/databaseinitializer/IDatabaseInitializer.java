/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.databaseinitializer;

/**
 * @author Silvan Wyss
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
