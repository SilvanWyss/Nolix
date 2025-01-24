package ch.nolix.system.sqlrawdata.adapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapter;
import ch.nolix.system.sqlrawdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawdata.datareader.DataReader;
import ch.nolix.system.sqlrawdata.datawriter.DataWriter;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;

public abstract class DataAdapter extends AbstractDataAdapter {

  private static final DatabaseInspector DATABASE_INSPECTOR = new DatabaseInspector();

  protected DataAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaReader schemaReader) {

    this(
      databaseName,
      sqlConnectionPool,
      DATABASE_INSPECTOR.createTableDefinitionsFrom(schemaReader));

    schemaReader.close();
  }

  private DataAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IContainer<TableViewDto> tableViews) {
    super(
      DataReader.forDatabaseNameAndSqlConnectionAndTableViews(
        databaseName,
        sqlConnectionPool.borrowResource(),
        tableViews),
      DataWriter.forDatabaseNameAndSqlConnectionAndTableViews(
        databaseName,
        sqlConnectionPool.borrowResource(),
        tableViews));
  }
}
