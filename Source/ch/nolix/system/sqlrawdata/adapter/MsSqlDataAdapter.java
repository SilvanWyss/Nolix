package ch.nolix.system.sqlrawdata.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter;

public final class MsSqlDataAdapter extends DataAdapter {

  private MsSqlDataAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(
      databaseName,
      sqlConnectionPool,
      MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnectionPool.borrowResource()));
  }

  public static MsSqlDataAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlDataAdapter(databaseName, sqlConnectionPool);
  }
}
