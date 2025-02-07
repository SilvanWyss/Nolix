package ch.nolix.system.objectschema.adapter;

import ch.nolix.coreapi.netapi.netconstantapi.PortCatalog;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;

public final class MsSqlSchemaAdapter extends AbstractSchemaAdapter {

  public static final int DEFAULT_PORT = PortCatalog.MS_SQL;

  MsSqlSchemaAdapter(
    String databaseName,
    final ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(databaseName, msSqlSchemaAdapter);
  }

  public static MsSqlSchemaAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(
        databaseName,
        sqlConnection));
  }
}
