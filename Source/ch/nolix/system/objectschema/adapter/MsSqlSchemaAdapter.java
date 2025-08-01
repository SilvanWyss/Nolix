package ch.nolix.system.objectschema.adapter;

import ch.nolix.coreapi.net.netconstant.PortCatalog;
import ch.nolix.coreapi.sql.connection.ISqlConnection;

public final class MsSqlSchemaAdapter extends AbstractSchemaAdapter {

  public static final int DEFAULT_PORT = PortCatalog.MS_SQL;

  MsSqlSchemaAdapter(
    String databaseName,
    final ch.nolix.system.sqlmidschema.adapter.SqlSchemaAdapter msSqlSchemaAdapter) {
    super(databaseName, msSqlSchemaAdapter);
  }

  public static MsSqlSchemaAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return //
    new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlmidschema.adapter.SqlSchemaAdapter.forDatabaseNameAndSqlConnection(
        databaseName,
        sqlConnection));
  }
}
