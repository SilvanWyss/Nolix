package ch.nolix.system.sqlrawschema.adapter;

import ch.nolix.core.sql.connection.UncloseableSqlConnection;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawschema.adapter.AbstractRawSchemaAdapter;
import ch.nolix.system.sqlrawschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlrawschema.schemareader.SchemaReader;
import ch.nolix.system.sqlrawschema.schemawriter.SchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public abstract class AbstractSqlRawSchemaAdapter extends AbstractRawSchemaAdapter {

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
