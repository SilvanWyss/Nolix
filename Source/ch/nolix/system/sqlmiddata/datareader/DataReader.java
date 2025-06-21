package ch.nolix.system.sqlmiddata.datareader;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.midschemaview.modelsearcher.DatabaseViewSearcher;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.middataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.IDatabaseViewSearcher;

public final class DataReader implements IDataReader {

  private static final IDatabaseViewSearcher DATABASE_VIEW_SEARCHER = new DatabaseViewSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseViewDto databaseView;

  private final InternalDataReader internalDataReader;

  private DataReader(
    final String databaseName,
    final DatabaseViewDto databaseView,
    final ISqlConnection sqlConnection) {

    Validator.assertThat(databaseView).thatIsNamed("database view").isNotNull();

    this.databaseView = databaseView;
    this.internalDataReader = new InternalDataReader(databaseName, sqlConnection);

    createCloseDependencyTo(sqlConnection);
  }

  public static DataReader forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
    final String databaseName,
    final DatabaseViewDto databaseView,
    final ISqlConnection sqlConnection) {
    return new DataReader(databaseName, databaseView, sqlConnection);
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

    final var multiBackReferenceColumnView = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    final var multiBackReferenceColumnId = multiBackReferenceColumnView.id();

    return internalDataReader.loadMultiBackReferenceEntries(entityId, multiBackReferenceColumnId);
  }

  @Override
  public IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var multiReferenceColumnView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);
    final var multiReferenceColumnId = multiReferenceColumnView.name();

    return internalDataReader.loadMultiReferenceEntries(entityId, multiReferenceColumnId, databaseView);
  }

  @Override
  public IContainer<Object> loadMultiValueValues(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var multiValueColumnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);

    return internalDataReader.loadMultiValueEntries(entityId, multiValueColumnView);
  }

  @Override
  public IContainer<EntityLoadingDto> loadEntities(final String tableName) {

    final var tableView = getTableViewByTableName(tableName);

    return internalDataReader.loadEntitiesOfTable(tableView);
  }

  @Override
  public EntityLoadingDto loadEntity(final String tableName, final String entityId) {

    final var tableView = getTableViewByTableName(tableName);

    return internalDataReader.loadEntity(tableView, entityId);
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

    final var columnview = getColumnViewByTableNameAndColumnName(tableName, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnview, value);
  }

  @Override
  public boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var columnview = getColumnViewByTableNameAndColumnName(tableName, columnName);

    return //
    internalDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnview,
      value,
      entitiesToIgnoreIds);
  }

  @Override
  public boolean tableContainsEntity(final String tableName, final String entityId) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, entityId);
  }

  private ColumnViewDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnName(databaseView, tableName, columnName);
  }

  private TableViewDto getTableViewByTableName(final String tableName) {
    return DATABASE_VIEW_SEARCHER.getTableViewByTableName(databaseView, tableName);
  }
}
