package ch.nolix.system.sqlschema.schemaadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlschema.sqlsyntax.SchemaQueryCreator;
import ch.nolix.system.sqlschema.sqlsyntax.SchemaStatementCreator;

public final class MsSqlSchemaAdapter extends SchemaAdapter {

  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(databaseName, sqlConnectionPool, new SchemaQueryCreator(), new SchemaStatementCreator());
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
