package ch.nolix.system.sqlschema.schemaadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlschema.mssqlquerycreator.MsSqlQueryCreator;
import ch.nolix.system.sqlschema.sqlsyntax.SchemaStatementCreator;

public final class MsSqlSchemaAdapter extends SchemaAdapter {

  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(databaseName, sqlConnectionPool, new MsSqlQueryCreator(), new SchemaStatementCreator());
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
