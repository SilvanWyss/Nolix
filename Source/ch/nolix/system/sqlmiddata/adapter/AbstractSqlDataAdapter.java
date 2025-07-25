package ch.nolix.system.sqlmiddata.adapter;

import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.middata.adapter.AbstractDataAdapter;
import ch.nolix.system.sqlmiddata.datareader.DataReader;
import ch.nolix.system.sqlmiddata.datawriter.DataWriter;
import ch.nolix.system.sqlmiddata.schemaviewloader.DatabaseSchemaViewLoader;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.sqlmiddata.schemaviewloader.IDatabaseSchemaViewLoader;

public abstract class AbstractSqlDataAdapter extends AbstractDataAdapter {

  private static final IDatabaseSchemaViewLoader DATABASE_SCHEMA_VIEW_LOADER = new DatabaseSchemaViewLoader();

  protected AbstractSqlDataAdapter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISchemaReader schemaReader) {

    this(
      databaseName,
      DATABASE_SCHEMA_VIEW_LOADER.loadDatabaseSchemaView(databaseName, schemaReader),
      sqlConnection);

    schemaReader.close();
  }

  private AbstractSqlDataAdapter(
    final String databaseName,
    final DatabaseViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {
    super(
      DataReader.forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnection),
      DataWriter.forDatabaseNameAndDatabaseViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnection));
  }
}
