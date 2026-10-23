/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndSqlDatabaseEngineCaptor}
 */
public interface IAndSqlDatabaseEngineCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndSqlDatabaseEngineCaptor} capture the given
   * sqlDatabaseEngine.
   * 
   * @param sqlDatabaseEngine
   * @return the successor of the current {@link IAndSqlDatabaseEngineCaptor}
   * @throws RuntimeException if the given sqlDatabaseEngine is null
   * @throws RuntimeException if the current {@link IAndSqlDatabaseEngineCaptor}
   *                          does not have a successor
   */
  S andSqlDatabaseEngine(SqlDatabaseEngine sqlDatabaseEngine);

  /**
   * @return the SQL database engine the current
   *         {@link IAndSqlDatabaseEngineCaptor} has captured
   * @throws RuntimeException if the current {@link IAndSqlDatabaseEngineCaptor}
   *                          has not captured a SQL database engine
   */
  SqlDatabaseEngine getSqlDatabaseEngine();
}
