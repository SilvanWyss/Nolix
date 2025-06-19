package ch.nolix.system.sqlmiddata.datareader;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.middata.schemaviewsearcher.TableViewDtoSearcher;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.middataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.ColumnViewDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.DatabaseViewDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.TableViewDto;
import ch.nolix.systemapi.middataapi.schemaviewsearcherapi.ITableViewDtoSearcher;

public final class DataReader implements IDataReader {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseViewDto databaseSchemaView;

  private final InternalDataReader internalDataReader;

  private DataReader(
    final String databaseName,
    final DatabaseViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {

    Validator.assertThat(databaseSchemaView).thatIsNamed(DatabaseViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    this.internalDataReader = new InternalDataReader(databaseName, sqlConnection);

    createCloseDependencyTo(sqlConnection);
  }

  public static DataReader forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
    final String databaseName,
    final DatabaseViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {
    return new DataReader(databaseName, databaseSchemaView, sqlConnection);
  }

  @Override
  public String getDatabaseName() {
    return internalDataReader.getDatabaseName();
  }

  @Override
  public Time getSchemaTimestamp() {
    return internalDataReader.getSchemaTimestamp();
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public IContainer<String> loadMultiBackReferenceBackReferencedEntityIds(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {

    final var multiBackReferenceColumnInfo = getColumnInfoByTableNameAndColumnName(tableName,
      multiBackReferenceColumnName);

    return internalDataReader.loadMultiBackReferenceEntries(entityId, multiBackReferenceColumnInfo);
  }

  @Override
  public IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return //
    internalDataReader.loadMultiReferenceEntries(
      entityId,
      getColumnInfoByTableNameAndColumnName(tableName, multiReferenceColumnName));
  }

  @Override
  public IContainer<Object> loadMultiValueValues(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    return internalDataReader.loadMultiValueEntries(
      entityId,
      getColumnInfoByTableNameAndColumnName(tableName, multiValueColumnName));
  }

  @Override
  public IContainer<EntityLoadingDto> loadEntities(final String tableName) {

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
  public boolean tableContainsEntityWithValueAtColumn(
    final String tableName,
    final String columnName,
    final String value) {

    final var columnInfo = getColumnInfoByTableNameAndColumnName(tableName, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnInfo, value);
  }

  @Override
  public boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
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
  public boolean tableContainsEntity(final String tableName, final String entityId) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, entityId);
  }

  private ColumnViewDto getColumnInfoByTableNameAndColumnName(
    final String tableName,
    final String columnName) {

    final var tableSchemaView = getTableSchemaViewByTableName(tableName);

    return TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableSchemaView, columnName);
  }

  private TableViewDto getTableSchemaViewByTableName(final String tableName) {
    return databaseSchemaView.tableSchemaViews().getStoredFirst(td -> td.name().equals(tableName));
  }
}
