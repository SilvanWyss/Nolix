package ch.nolix.core.sql.connection;

import java.sql.Connection;

import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public final class MsSqlConnection extends SqlConnection {

  public static final SqlDatabaseEngine SQL_DATABASE_ENGINE = SqlDatabaseEngine.MSSQL;

  private static final String MSSQL_DATABASE_ENINGE_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

  public MsSqlConnection(
    final Connection connection) {
    super(SQL_DATABASE_ENGINE, connection);
  }

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

  @Override
  protected String getSqlDatabaseEngineDriverClass() {
    return MSSQL_DATABASE_ENINGE_DRIVER_CLASS;
  }
}
