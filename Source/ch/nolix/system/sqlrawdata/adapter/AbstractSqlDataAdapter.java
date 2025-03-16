package ch.nolix.system.sqlrawdata.adapter;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapter;
import ch.nolix.system.sqlrawdata.datareader.DataReader;
import ch.nolix.system.sqlrawdata.datawriter.DataWriter;
import ch.nolix.system.sqlrawdata.schemaviewloader.DatabaseSchemaViewLoader;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.sqlrawdataapi.schemaviewloaderapi.IDatabaseSchemaViewLoader;

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
    final DatabaseSchemaViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {
    super(
      DataReader.forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnection),
      DataWriter.forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnection));
  }
}
