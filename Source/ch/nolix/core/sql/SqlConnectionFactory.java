//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;
import ch.nolix.core.sql.connection.MsSqlConnection;
import ch.nolix.core.sql.connection.SqlConnection;

//class
public final class SqlConnectionFactory {

  //method
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

  //method
  private MsSqlConnection createMsSqlConnectionForSqlConnectionPool(final SqlConnectionPool sqlConnectionPool) {
    return //
    new MsSqlConnection(
      sqlConnectionPool.getIpOrDomain(),
      sqlConnectionPool.getPort(),
      sqlConnectionPool.getLoginName(),
      sqlConnectionPool.getLoginPassword());
  }
}
