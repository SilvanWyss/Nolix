package ch.nolix.system.sqlmidschema.adapter;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlschema.querycreator.QueryCreator;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public final class MsSqlSchemaAdapter extends AbstractSqlSchemaAdapter {

  private static final IQueryCreator QUERY_CREATOR = new QueryCreator();

  private MsSqlSchemaAdapter(final String databaseName, final ISqlConnection sqlConnection) {
    super(databaseName, sqlConnection, QUERY_CREATOR);
  }

  public static MsSqlSchemaAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnection);
  }
}
