/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connection;

import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public final class MsSqlConnection extends AbstractSqlConnection {
  private static final SqlDatabaseEngine SQL_DATABASE_ENGINE = SqlDatabaseEngine.MS_SQL;

  private static final String MSSQL_DATABASE_ENINGE_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

  private MsSqlConnection(final int port, final String userName, final String userPassword) {
    super(SQL_DATABASE_ENGINE, port, userName, userPassword);
  }

  private MsSqlConnection(final String host, final int port, final String userName, final String userPassword) {
    super(SQL_DATABASE_ENGINE, host, port, userName, userPassword);
  }

  public static MsSqlConnection toHostAndPortAndWithUserNameAndUserPassword(
    final String host,
    final int port,
    final String userName,
    final String userPassword) {
    return new MsSqlConnection(host, port, userName, userPassword);
  }

  public static MsSqlConnection toLocalHostAndPortAndWithUserNameAndUserPassword(
    final int port,
    final String userName,
    final String userPassword) {
    return new MsSqlConnection(port, userName, userPassword);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getSqlDatabaseEngineDriverClass() {
    return MSSQL_DATABASE_ENINGE_DRIVER_CLASS;
  }
}
