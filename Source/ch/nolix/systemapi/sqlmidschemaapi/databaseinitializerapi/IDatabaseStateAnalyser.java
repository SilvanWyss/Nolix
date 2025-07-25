package ch.nolix.systemapi.sqlmidschemaapi.databaseinitializerapi;

import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.systemapi.midschemaapi.databaseinitializerapi.DatabaseState;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 */
public interface IDatabaseStateAnalyser {

  /**
   * @param databaseName
   * @param sqlConnection
   * @return the state of the database with the given databaseName using the given
   *         sqlConnection.
   * @throws RuntimeException if the given sqlConnection is null or closed.
   */
  DatabaseState getDatabasState(String databaseName, ISqlConnection sqlConnection);
}
