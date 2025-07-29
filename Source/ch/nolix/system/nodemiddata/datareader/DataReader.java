package ch.nolix.system.nodemiddata.datareader;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.midschemaview.modelsearcher.DatabaseViewSearcher;
import ch.nolix.systemapi.middata.adapter.IDataReader;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.midschemaview.modelsearcher.IDatabaseViewSearcher;
import ch.nolix.systemapi.time.moment.ITime;

public final class DataReader implements IDataReader {

  private static final IDatabaseViewSearcher DATABASE_VIEW_SEARCHER = new DatabaseViewSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseViewDto databaseView;

  private final InternalDataReader internalDataReader;

  private DataReader(final IMutableNode<?> nodeDatabase, final DatabaseViewDto databaseView) {

    Validator.assertThat(databaseView).thatIsNamed("database view").isNotNull();

    this.databaseView = databaseView;
    this.internalDataReader = new InternalDataReader(nodeDatabase);
  }

  public static DataReader forNodeDatabaseAndDatabaseView(
    final IMutableNode<?> nodeDatabase,
    final DatabaseViewDto databaseView) {
    return new DataReader(nodeDatabase, databaseView);
  }

  @Override
  public String getDatabaseName() {
    return internalDataReader.getDatabaseName();
  }

  @Override
  public ITime getSchemaTimestamp() {
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

    final var multiBackReferenceColumnInfo = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    return //
    internalDataReader.loadMultiBackReferenceBackReferencedEntityIds(tableName, entityId, multiBackReferenceColumnInfo);
  }

  @Override
  public IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var multiReferenceColumnView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);

    return internalDataReader.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnView);
  }

  @Override
  public IContainer<Object> loadMultiValueValues(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var columnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);

    return internalDataReader.loadMultiValueEntries(tableName, entityId, columnView);
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

    final var columnView = getColumnViewByTableNameAndColumnName(tableName, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnView, value);
  }

  @Override
  public boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var columnView = getColumnViewByTableNameAndColumnName(tableName, columnName);

    return //
    internalDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnView,
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
