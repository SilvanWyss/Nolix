package ch.nolix.system.sqlrawschema.schemaadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;

public final class MsSqlSchemaAdapter extends SchemaAdapter {

  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(
      databaseName,
      sqlConnectionPool,
      ch.nolix.system.sqlschema.schemaadapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          databaseName,
          sqlConnectionPool));
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
