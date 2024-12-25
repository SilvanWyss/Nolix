package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.datadto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;

public final class DataReader implements IDataReader {

  private final ICloseController closeController = CloseController.forElement(this);

  private final InternalDataReader internalDataReader;

  private final IContainer<ITableInfo> tableInfos;

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

  public static DataReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {
    return new DataReader(databaseName, sqlConnectionPool.borrowResource(), tableInfos,
      sqlSyntaxProvider);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public Time getSchemaTimestamp() {
    return internalDataReader.getSchemaTimestamp();
  }

  @Override
  public IContainer<String> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {

    final var multiBackReferenceColumnInfo = getColumnInfoByTableNameAndColumnName(tableName,
      multiBackReferenceColumnName);

    return internalDataReader.loadMultiBackReferenceEntries(entityId, multiBackReferenceColumnInfo);
  }

  @Override
  public IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return internalDataReader.loadMultiReferenceEntries(
      entityId,
      getColumnInfoByTableNameAndColumnName(tableName, multiReferenceColumnName));
  }

  @Override
  public IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    return internalDataReader.loadMultiValueEntries(
      entityId,
      getColumnInfoByTableNameAndColumnName(tableName, multiValueColumnName));
  }

  @Override
  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final String tableName) {
    return internalDataReader.loadEntitiesOfTable(getTableInfoByTableName(tableName));
  }

  @Override
  public EntityLoadingDto loadEntity(final String tableName, final String id) {
    return internalDataReader.loadEntity(getTableInfoByTableName(tableName), id);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {

    final var columnInfo = getColumnInfoByTableNameAndColumnName(tableName, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnInfo, value);
  }

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

  @Override
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, id);
  }

  private IColumnInfo getColumnInfoByTableNameAndColumnName(
    final String tableName,
    final String columnName) {
    return getTableInfoByTableName(tableName).getColumnInfoByColumnName(columnName);
  }

  private ITableInfo getTableInfoByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
