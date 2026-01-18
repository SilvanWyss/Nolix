/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.adapter;

import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmidschema.adapter.SqlSchemaAdapter;

/**
 * @author Silvan Wyss
 */
public final class MsSqlDataAdapter extends AbstractSqlDataAdapter {
  private MsSqlDataAdapter(final String databaseName, final ISqlConnection sqlConnection) {
    super(
      databaseName,
      sqlConnection,
      SqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));
  }

  public static MsSqlDataAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new MsSqlDataAdapter(databaseName, sqlConnection);
  }
}
