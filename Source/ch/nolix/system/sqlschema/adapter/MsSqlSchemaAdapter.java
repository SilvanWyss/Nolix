package ch.nolix.system.sqlschema.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlschema.mssqlquerycreator.MsSqlQueryCreator;
import ch.nolix.system.sqlschema.statementcreator.StatementCreator;

public final class MsSqlSchemaAdapter extends AbstractSchemaAdapter {

  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(databaseName, sqlConnectionPool, new MsSqlQueryCreator(), new StatementCreator());
  }

  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
