package ch.nolix.system.sqlmiddata.adapter;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlmidschema.adapter.MsSqlSchemaAdapter;

public final class MsSqlDataAdapter extends AbstractSqlDataAdapter {

  private MsSqlDataAdapter(final String databaseName, final ISqlConnection sqlConnection) {
    super(
      databaseName,
      sqlConnection,
      MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));
  }

  public static MsSqlDataAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new MsSqlDataAdapter(databaseName, sqlConnection);
  }
}
