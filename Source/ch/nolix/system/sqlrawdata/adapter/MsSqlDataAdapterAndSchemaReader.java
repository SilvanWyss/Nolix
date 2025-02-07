package ch.nolix.system.sqlrawdata.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapterAndSchemaReader;
import ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter;

public final class MsSqlDataAdapterAndSchemaReader extends AbstractDataAdapterAndSchemaReader {

  private MsSqlDataAdapterAndSchemaReader(
    final MsSqlDataAdapter msSqlDataAdapter,
    final MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(msSqlDataAdapter, msSqlSchemaAdapter);
  }

  public static MsSqlDataAdapterAndSchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlDataAdapterAndSchemaReader(
      MsSqlDataAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
      MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName,
        sqlConnectionPool.borrowResource()));
  }
}
