package ch.nolix.system.objectschema.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalog;

public final class MsSqlSchemaAdapter extends AbstractSchemaAdapter {

  public static final int DEFAULT_PORT = PortCatalog.MS_SQL;

  MsSqlSchemaAdapter(
    String databaseName,
    final ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(databaseName, msSqlSchemaAdapter);
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlrawschema.adapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          databaseName,
          sqlConnectionPool));
  }
}
