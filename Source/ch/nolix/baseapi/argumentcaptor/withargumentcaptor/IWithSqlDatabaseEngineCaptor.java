/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a
 *            {@link IWithSqlDatabaseEngineCaptor}
 */
public interface IWithSqlDatabaseEngineCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the SQL database engine the current
   *         {@link IWithSqlDatabaseEngineCaptor} has captured
   * @throws RuntimeException if the current {@link IWithSqlDatabaseEngineCaptor}
   *                          has not captured a SQL database engine
   */
  SqlDatabaseEngine getSqlDatabaseEngine();

  /**
   * Lets the current {@link IWithSqlDatabaseEngineCaptor} capture the given
   * sqlDatabaseEngine.
   * 
   * @param sqlDatabaseEngine
   * @return the successor of the current {@link IWithSqlDatabaseEngineCaptor}
   * @throws RuntimeException if the given sqlDatabaseEngine is null
   * @throws RuntimeException if the current {@link IWithSqlDatabaseEngineCaptor}
   *                          does not have a successor
   */
  S withSqlDatabaseEngine(SqlDatabaseEngine sqlDatabaseEngine);
}
