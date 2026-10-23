/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.connection;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public interface ISqlConnection extends GroupCloseable {
  /**
   * Executes the given statement.
   * 
   * @param statement
   */
  void executeStatement(String statement);

  /**
   * Executes the given statements.
   * 
   * @param statements
   */
  void executeStatements(ExtendedIterable<String> statements);

  /**
   * Executes the given statements.
   * 
   * @param statements
   */
  void executeStatements(String... statements);

  /**
   * @return the {@link SqlDatabaseEngine} of the current {@link ISqlConnection}
   */
  SqlDatabaseEngine getDatabaseEngine();

  /**
   * @param query
   * @return the records from the given query
   */
  ExtendedIterable<ISqlRecord> getRecordsFromQuery(String query);

  /**
   * @param query
   * @return the single record from the given query
   * @throws RuntimeException if the given query does not return exactly 1 record
   */
  ISqlRecord getSingleRecordFromQuery(String query);
}
