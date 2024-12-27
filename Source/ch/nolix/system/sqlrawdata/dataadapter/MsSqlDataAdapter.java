package ch.nolix.system.sqlrawdata.dataadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter;

public final class MsSqlDataAdapter extends DataAdapter {

  private MsSqlDataAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(
      databaseName,
      sqlConnectionPool,
      MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool));
  }

  public static MsSqlDataAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlDataAdapter(databaseName, sqlConnectionPool);
  }
}
