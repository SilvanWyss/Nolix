/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.adapter;

import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.middata.adapter.AbstractDataAdapterAndSchemaReader;
import ch.nolix.system.sqlmidschema.adapter.SqlSchemaAdapter;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;

/**
 * @author Silvan Wyss
 */
public final class MsSqlDataAdapterAndSchemaReader extends AbstractDataAdapterAndSchemaReader {
  private final ISqlConnection sqlConnection;

  private MsSqlDataAdapterAndSchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    super(
      MsSqlDataAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection),
      SqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));

    this.sqlConnection = sqlConnection;
  }

  public static MsSqlDataAdapterAndSchemaReader forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new MsSqlDataAdapterAndSchemaReader(databaseName, sqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IDataAdapterAndSchemaReader createEmptyCopy() {
    return forDatabaseNameAndSqlConnection(getDatabaseName(), sqlConnection);
  }
}
