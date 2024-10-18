package ch.nolix.system.sqlrawdata.dataandschemaadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.baserawschema.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqlrawdata.dataadapter.MsSqlDataAdapter;
import ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter;

public final class MsSqlDataAndSchemaAdapter extends BaseDataAndSchemaAdapter {

  private MsSqlDataAndSchemaAdapter(
    final MsSqlDataAdapter msSqlDataAdapter,
    final MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(msSqlDataAdapter, msSqlSchemaAdapter);
  }

  public static MsSqlDataAndSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlDataAndSchemaAdapter(
      MsSqlDataAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
      MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool));
  }
}
