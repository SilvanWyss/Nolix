package ch.nolix.system.sqlrawschema.adapter;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlschema.mssqlquerycreator.MsSqlQueryCreator;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public final class MsSqlSchemaAdapter extends AbstractSqlRawSchemaAdapter {

  private static final IQueryCreator QUERY_CREATOR = new MsSqlQueryCreator();

  private MsSqlSchemaAdapter(final String databaseName, final ISqlConnection sqlConnection) {
    super(databaseName, sqlConnection, QUERY_CREATOR);
  }

  public static MsSqlSchemaAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnection);
  }
}
