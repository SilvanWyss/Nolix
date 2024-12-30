package ch.nolix.system.objectschema.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;

public final class MsSqlSchemaAdapter extends AbstractSchemaAdapter {

  public static final int DEFAULT_PORT = PortCatalogue.MS_SQL;

  MsSqlSchemaAdapter(
    String databaseName,
    final ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(databaseName, msSqlSchemaAdapter);
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          databaseName,
          sqlConnectionPool));
  }
}
