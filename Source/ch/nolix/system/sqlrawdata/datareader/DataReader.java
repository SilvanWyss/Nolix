//package declaration
package ch.nolix.system.sqlrawdata.datareader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;

//class
public final class DataReader implements IDataReader {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private final InternalDataReader internalDataReader;

  //multi-attribute
  private final IContainer<ITableInfo> tableInfos;

  //constructor
  private DataReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDataReader = new InternalDataReader(databaseName, sqlConnection, sqlSyntaxProvider);
    this.tableInfos = tableInfos;

    createCloseDependencyTo(sqlConnection);
  }

  //static method
  public static DataReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {
    return new DataReader(databaseName, sqlConnectionPool.borrowResource(), tableInfos,
      sqlSyntaxProvider);
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public Time getSchemaTimestamp() {
    return internalDataReader.getSchemaTimestamp();
  }

  //method
  @Override
  public IContainer<String> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {

    final var multiBackReferenceColumnInfo = getColumnInfoByTableNameAndColumnName(tableName,
      multiBackReferenceColumnName);

    return internalDataReader.loadMultiBackReferenceEntries(entityId, multiBackReferenceColumnInfo);
  }

  //method
  @Override
  public IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return internalDataReader.loadMultiReferenceEntries(
      entityId,
      getColumnInfoByTableNameAndColumnName(tableName, multiReferenceColumnName));
  }

  //method
  @Override
  public IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    return internalDataReader.loadMultiValueEntries(
      entityId,
      getColumnInfoByTableNameAndColumnName(tableName, multiValueColumnName));
  }

  //method
  @Override
  public IContainer<ILoadedEntityDto> loadEntitiesOfTable(final String tableName) {
    return internalDataReader.loadEntitiesOfTable(getTableInfoByTableName(tableName));
  }

  //method
  @Override
  public ILoadedEntityDto loadEntity(final String tableName, final String id) {
    return internalDataReader.loadEntity(getTableInfoByTableName(tableName), id);
  }

  //method
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  @Override
  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {

    final var columnInfo = getColumnInfoByTableNameAndColumnName(tableName, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnInfo, value);
  }

  //method
  @Override
  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var columnInfo = getColumnInfoByTableNameAndColumnName(tableName, columnName);

    return //
    internalDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnInfo,
      value,
      entitiesToIgnoreIds);
  }

  //method
  @Override
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, id);
  }

  //method
  private IColumnInfo getColumnInfoByTableNameAndColumnName(
    final String tableName,
    final String columnName) {
    return getTableInfoByTableName(tableName).getColumnInfoByColumnName(columnName);
  }

  //method
  private ITableInfo getTableInfoByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
