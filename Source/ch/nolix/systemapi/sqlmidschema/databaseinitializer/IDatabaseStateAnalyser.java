package ch.nolix.systemapi.sqlmidschema.databaseinitializer;

import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.systemapi.midschema.databaseinitializer.DatabaseState;

/**
 * @author Silvan Wyss
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
