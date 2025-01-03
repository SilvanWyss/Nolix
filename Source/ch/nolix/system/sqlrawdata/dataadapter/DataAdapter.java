package ch.nolix.system.sqlrawdata.dataadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapter;
import ch.nolix.system.sqlrawdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawdata.datareader.DataReader;
import ch.nolix.system.sqlrawdata.datawriter.DataWriter;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

public abstract class DataAdapter extends AbstractDataAdapter {

  private static final DatabaseInspector DATABASE_INSPECTOR = new DatabaseInspector();

  protected DataAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaAdapter schemaAdapter) {

    this(
      databaseName,
      sqlConnectionPool,
      DATABASE_INSPECTOR.createTableDefinitionsFrom(schemaAdapter));

    schemaAdapter.close();
  }

  private DataAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IContainer<ITableView> tableViews) {
    super(
      DataReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
        databaseName,
        sqlConnectionPool,
        tableViews),
      DataWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
        databaseName,
        sqlConnectionPool,
        tableViews));
  }
}
