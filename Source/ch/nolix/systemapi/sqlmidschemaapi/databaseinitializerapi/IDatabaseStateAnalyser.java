package ch.nolix.systemapi.sqlmidschemaapi.databaseinitializerapi;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 */
public interface IDatabaseStateAnalyser {

  /**
   * @param databaseName
   * @param sqlConnection
   * @param queryCreator
   * @return the state of the database with the given databaseName using the given
   *         sqlConnection and queryCreator.
   * @throws RuntimeException if the given sqlConnection is null or closed.
   * @throws RuntimeException if the given queryCreator is null.
   */
  DatabaseState getDatabasState(String databaseName, ISqlConnection sqlConnection, IQueryCreator queryCreator);
}
