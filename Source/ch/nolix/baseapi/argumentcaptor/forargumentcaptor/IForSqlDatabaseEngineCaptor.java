/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForSqlDatabaseEngineCaptor}
 */
public interface IForSqlDatabaseEngineCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForSqlDatabaseEngineCaptor} capture the given
   * sqlDatabaseEngine.
   * 
   * @param sqlDatabaseEngine
   * @return the successor of the current {@link IForSqlDatabaseEngineCaptor}
   * @throws RuntimeException if the given sqlDatabaseEngine is null
   * @throws RuntimeException if the current {@link IForSqlDatabaseEngineCaptor}
   *                          does not have a successor
   */
  S forSqlDatabaseEngine(SqlDatabaseEngine sqlDatabaseEngine);

  /**
   * @return the SQL database engine the current
   *         {@link IForSqlDatabaseEngineCaptor} has captured
   * @throws RuntimeException if the current {@link IForSqlDatabaseEngineCaptor}
   *                          has not captured a SQL database engine
   */
  SqlDatabaseEngine getSqlDatabaseEngine();
}
