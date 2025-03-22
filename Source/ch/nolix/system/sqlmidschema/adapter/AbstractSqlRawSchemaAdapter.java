package ch.nolix.system.sqlmidschema.adapter;

import ch.nolix.core.sql.connection.UncloseableSqlConnection;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.midschema.adapter.AbstractSchemaAdapter;
import ch.nolix.system.sqlmidschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlmidschema.schemareader.SchemaReader;
import ch.nolix.system.sqlmidschema.schemawriter.SchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public abstract class AbstractSqlRawSchemaAdapter extends AbstractSchemaAdapter {

  protected AbstractSqlRawSchemaAdapter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator sqlSchemaQueryCreator) {

    super(
      DatabaseInitializer.forDatabaseNameAndSqlConnectionAndSqlSchemaQueryCreator(
        databaseName,
        UncloseableSqlConnection.forSqlConnection(sqlConnection),
        sqlSchemaQueryCreator),
      () -> //
      SchemaReader.forDatabaseNameAndSqlConnectionAndSqlSchemaQueryCreator(
        databaseName,
        sqlConnection,
        sqlSchemaQueryCreator),
      () -> SchemaWriter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));
  }
}
