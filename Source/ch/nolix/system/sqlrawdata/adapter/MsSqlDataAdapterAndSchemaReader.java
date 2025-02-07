package ch.nolix.system.sqlrawdata.adapter;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapterAndSchemaReader;
import ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter;

public final class MsSqlDataAdapterAndSchemaReader extends AbstractDataAdapterAndSchemaReader {

  private MsSqlDataAdapterAndSchemaReader(
    final MsSqlDataAdapter msSqlDataAdapter,
    final MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(msSqlDataAdapter, msSqlSchemaAdapter);
  }

  public static MsSqlDataAdapterAndSchemaReader forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return //
    new MsSqlDataAdapterAndSchemaReader(
      MsSqlDataAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection),
      MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));
  }
}
