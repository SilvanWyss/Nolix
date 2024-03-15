//package declaration
package ch.nolix.system.sqlrawdata.dataadapter;

//own imports
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.baserawdatabase.databaseadapter.BaseDataAdapter;
import ch.nolix.system.sqlrawdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawdata.datareader.DataReader;
import ch.nolix.system.sqlrawdata.datawriter.DataWriter;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;

//class
public abstract class DataAdapter extends BaseDataAdapter {

  //constant
  private static final DatabaseInspector DATABASE_INSPECTOR = new DatabaseInspector();

  //constructor
  protected DataAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaAdapter schemaAdapter,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    this(
      databaseName,
      sqlConnectionPool,
      DATABASE_INSPECTOR.createTableDefinitionsFrom(schemaAdapter),
      sqlSyntaxProvider);

    schemaAdapter.close();
  }

  //constructor
  private DataAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {
    super(
      DataReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
        databaseName,
        sqlConnectionPool,
        tableInfos,
        sqlSyntaxProvider),
      DataWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
        databaseName,
        sqlConnectionPool,
        tableInfos,
        sqlSyntaxProvider));
  }
}
