package ch.nolix.core.sql.connectionpool;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;
import ch.nolix.core.sql.connection.MsSqlConnection;
import ch.nolix.core.sql.connection.SqlConnection;

public final class SqlConnectionFactory {

  public SqlConnection createSqlConnectionForSqlConnectionPool(final SqlConnectionPool sqlConnectionPool) {
    return switch (sqlConnectionPool.getSqlDatabaseEngine()) {
      case MSSQL ->
        createMsSqlConnectionForSqlConnectionPool(sqlConnectionPool);
      case MYSQL, ORACLE ->
        throw UnsupportedCaseException.forCase(sqlConnectionPool.getSqlDatabaseEngine());
      default ->
        throw InvalidArgumentException.forArgument(sqlConnectionPool.getSqlDatabaseEngine());
    };
  }

  private MsSqlConnection createMsSqlConnectionForSqlConnectionPool(final SqlConnectionPool sqlConnectionPool) {
    return //
    new MsSqlConnection(
      sqlConnectionPool.getIpOrDomain(),
      sqlConnectionPool.getPort(),
      sqlConnectionPool.getLoginName(),
      sqlConnectionPool.getLoginPassword());
  }
}
