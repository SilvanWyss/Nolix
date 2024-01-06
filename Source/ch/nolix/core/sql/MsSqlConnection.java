//package declaration
package ch.nolix.core.sql;

//Java imports
import java.sql.Connection;

import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public final class MsSqlConnection extends SqlConnection {

  //constant
  public static final SqlDatabaseEngine SQL_DATABASE_ENGINE = SqlDatabaseEngine.MSSQL;

  //constant
  private static final String MSSQL_DATABASE_ENINGE_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

  //constructor
  public MsSqlConnection(
    final Connection connection) {
    super(SQL_DATABASE_ENGINE, connection);
  }

  //constructor
  public MsSqlConnection(
    final int port,
    final String userName,
    final String userPassword) {
    super(
      SQL_DATABASE_ENGINE,
      port,
      userName,
      userPassword);
  }

  //constructor
  public MsSqlConnection(
    final String ip,
    final int port,
    final String userName,
    final String userPassword) {
    super(
      SQL_DATABASE_ENGINE,
      ip,
      port,
      userName,
      userPassword);
  }

  //constructor
  public MsSqlConnection(
    final String ip,
    final int port,
    final String userName,
    final String userPassword,
    final SqlConnectionPool parentQslConnectionPool) {
    super(
      SQL_DATABASE_ENGINE,
      ip,
      port,
      userName,
      userPassword,
      parentQslConnectionPool);
  }

  //method
  @Override
  protected String getSqlDatabaseEngineDriverClass() {
    return MSSQL_DATABASE_ENINGE_DRIVER_CLASS;
  }
}
