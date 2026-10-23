/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.sqltool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.sql.connection.ISqlConnection;

/**
 * @author Silvan Wyss
 */
public interface ISqlCollector extends Clearable {
  /**
   * Adds the given sqlstatement to the current {@link ISqlCollector}.
   * 
   * @param sqlstatement
   * @return the current {@link ISqlCollector}
   */
  ISqlCollector addSqlStatement(String sqlstatement);

  /**
   * Adds the given sqlStatements to the current {@link ISqlCollector}.
   * 
   * @param sqlStatements
   * @return the current {@link ISqlCollector}
   */
  ISqlCollector addSqlStatements(Iterable<String> sqlStatements);

  /**
   * Adds the given sqlStatements to the current {@link ISqlCollector}.
   * 
   * @param sqlStatements
   * @return the current {@link ISqlCollector}
   */
  ISqlCollector addSqlStatements(String... sqlStatements);

  /**
   * Executes the SQL statements of the current {@link ISqlCollector} using the
   * given sqlConnection and clears the current {@link ISqlCollector}.
   * 
   * @param sqlConnection
   */
  void executeAndClearUsingConnection(ISqlConnection sqlConnection);

  /**
   * @return the SQL statements of the current {@link ISqlCollector}
   */
  ExtendedIterable<String> getSqlStatements();
}
