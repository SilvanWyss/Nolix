package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

public final class DataReader implements IDataReader {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseSchemaViewDto databaseSchemaView;

  private final InternalDataReader internalDataReader;

  private DataReader(
    final String databaseName,
    final DatabaseSchemaViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {

    GlobalValidator.assertThat(databaseSchemaView).thatIsNamed(DatabaseSchemaViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    this.internalDataReader = new InternalDataReader(databaseName, sqlConnection);

    createCloseDependencyTo(sqlConnection);
  }

  public static DataReader forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
    final String databaseName,
    final DatabaseSchemaViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {
    return new DataReader(databaseName, databaseSchemaView, sqlConnection);
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

    final var tableSchemaView = getTableSchemaViewByTableName(tableName);

    return internalDataReader.loadEntitiesOfTable(tableSchemaView);
  }

  @Override
  public EntityLoadingDto loadEntity(final String tableName, final String id) {

    final var tableSchemaView = getTableSchemaViewByTableName(tableName);

    return internalDataReader.loadEntity(tableSchemaView, id);
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

  private ColumnSchemaViewDto getColumnInfoByTableNameAndColumnName(
    final String tableName,
    final String columnName) {

    final var tableSchemaView = getTableSchemaViewByTableName(tableName);

    return TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableSchemaView, columnName);
  }

  private TableSchemaViewDto getTableSchemaViewByTableName(final String tableName) {
    return databaseSchemaView.tableSchemaViews().getStoredFirst(td -> td.name().equals(tableName));
  }
}
