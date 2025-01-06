package ch.nolix.system.sqlrawschema.schemaadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlschema.mssqlquerycreator.MsSqlQueryCreator;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public final class MsSqlSchemaAdapter extends SchemaAdapter {

  private static final IQueryCreator QUERY_CREATOR = new MsSqlQueryCreator();

  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(
      databaseName,
      sqlConnectionPool,
      ch.nolix.system.sqlschema.adapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          databaseName,
          sqlConnectionPool),
      QUERY_CREATOR);
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
