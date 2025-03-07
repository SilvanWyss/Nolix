package ch.nolix.system.sqlrawdata.adapter;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapterAndSchemaReader;
import ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public final class MsSqlDataAdapterAndSchemaReader extends AbstractDataAdapterAndSchemaReader {

  private final ISqlConnection sqlConnection;

  private MsSqlDataAdapterAndSchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection) {

    super(
      MsSqlDataAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection),
      MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));

    this.sqlConnection = sqlConnection;
  }

  public static MsSqlDataAdapterAndSchemaReader forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new MsSqlDataAdapterAndSchemaReader(databaseName, sqlConnection);
  }

  @Override
  public IDataAdapterAndSchemaReader createEmptyCopy() {
    return forDatabaseNameAndSqlConnection(getDatabaseName(), sqlConnection);
  }
}
