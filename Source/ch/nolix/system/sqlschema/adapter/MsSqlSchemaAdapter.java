package ch.nolix.system.sqlschema.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlschema.querycreator.QueryCreator;

public final class MsSqlSchemaAdapter extends AbstractSchemaAdapter {

  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(databaseName, sqlConnectionPool, new QueryCreator());
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
