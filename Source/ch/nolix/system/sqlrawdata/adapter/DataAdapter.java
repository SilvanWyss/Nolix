package ch.nolix.system.sqlrawdata.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapter;
import ch.nolix.system.sqlrawdata.datareader.DataReader;
import ch.nolix.system.sqlrawdata.datawriter.DataWriter;
import ch.nolix.system.sqlrawdata.schemaviewloader.DatabaseSchemaViewLoader;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlrawdataapi.schemaviewloaderapi.IDatabaseSchemaViewLoader;

public abstract class DataAdapter extends AbstractDataAdapter {

  private static final IDatabaseSchemaViewLoader DATABASE_SCHEMA_VIEW_LOADER = new DatabaseSchemaViewLoader();

  protected DataAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaReader schemaReader) {

    this(
      databaseName,
      DATABASE_SCHEMA_VIEW_LOADER.loadDatabaseSchemaView(databaseName, schemaReader),
      sqlConnectionPool);

    schemaReader.close();
  }

  private DataAdapter(
    final String databaseName,
    final DatabaseSchemaViewDto databaseSchemaView,
    final SqlConnectionPool sqlConnectionPool) {
    super(
      DataReader.forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnectionPool.borrowResource()),
      DataWriter.forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnectionPool.borrowResource()));
  }
}
