/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connectionpool;

import ch.nolix.base.sql.connection.AbstractSqlConnection;
import ch.nolix.base.sql.connection.MsSqlConnection;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnsupportedCaseException;

/**
 * @author Silvan Wyss
 */
public final class SqlConnectionFactory {
  private SqlConnectionFactory() {
  }

  public static AbstractSqlConnection createSqlConnectionForSqlConnectionPool(
    final SqlConnectionPool sqlConnectionPool) {
    return switch (sqlConnectionPool.getSqlDatabaseEngine()) {
      case MS_SQL ->
        createMsSqlConnectionForSqlConnectionPool(sqlConnectionPool);
      case MY_SQL, ORACLE ->
        throw UnsupportedCaseException.forCase(sqlConnectionPool.getSqlDatabaseEngine());
      default ->
        throw InvalidArgumentException.forArgument(sqlConnectionPool.getSqlDatabaseEngine());
    };
  }

  private static MsSqlConnection createMsSqlConnectionForSqlConnectionPool(final SqlConnectionPool sqlConnectionPool) {
    return //
    MsSqlConnection.toHostAndPortAndWithUserNameAndUserPassword(
      sqlConnectionPool.getHost(),
      sqlConnectionPool.getPort(),
      sqlConnectionPool.getLoginName(),
      sqlConnectionPool.getLoginPassword());
  }
}
