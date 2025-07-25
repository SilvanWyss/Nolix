package ch.nolix.system.sqlmidschema.adapter;

import ch.nolix.core.sql.connection.UncloseableSqlConnection;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.midschema.adapter.AbstractSchemaAdapter;
import ch.nolix.system.sqlmidschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlmidschema.schemareader.SchemaReader;
import ch.nolix.system.sqlmidschema.schemawriter.SchemaWriter;

public final class SqlSchemaAdapter extends AbstractSchemaAdapter {

  private SqlSchemaAdapter(final String databaseName, final ISqlConnection sqlConnection) {
    super(
      DatabaseInitializer.forDatabaseNameAndSqlConnection(
        databaseName,
        UncloseableSqlConnection.forSqlConnection(sqlConnection)),
      () -> //
      SchemaReader.forDatabaseNameAndSqlConnection(
        databaseName,
        sqlConnection),
      () -> SchemaWriter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));
  }

  public static SqlSchemaAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new SqlSchemaAdapter(databaseName, sqlConnection);
  }
}
