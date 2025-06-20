package ch.nolix.system.nodemiddata.datareader;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.midschemaview.modelsearcher.TableViewSearcher;
import ch.nolix.systemapi.middataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.ITableViewSearcher;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataReader implements IDataReader {

  private static final ITableViewSearcher TABLE_VIEW_SEARCHER = new TableViewSearcher();

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

    final var tableView = getTableSchemaViewByTableName(tableName);

    final var multiBackReferenceColumnInfo = //
    TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableView, multiBackReferenceColumnName);

    return //
    internalDataReader.loadMultiBackReferenceBackReferencedEntityIds(tableName, entityId, multiBackReferenceColumnInfo);
  }

  @Override
  public IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableInfo, multiReferenceColumnName);

    return internalDataReader.loadMultiReferenceEntries(tableInfo, entityId, columnView);
  }

  @Override
  public IContainer<Object> loadMultiValueValues(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    return internalDataReader.loadMultiValueEntries(tableInfo, entityId, columnView);
  }

  @Override
  public IContainer<EntityLoadingDto> loadEntities(final String tableName) {
    return internalDataReader.loadEntitiesOfTable(getTableSchemaViewByTableName(tableName));
  }

  @Override
  public EntityLoadingDto loadEntity(final String tableName, final String id) {
    return internalDataReader.loadEntity(getTableSchemaViewByTableName(tableName), id);
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

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableInfo, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableInfo, columnView, value);
  }

  @Override
  public boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableInfo, columnName);

    return //
    internalDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableInfo,
      columnView,
      value,
      entitiesToIgnoreIds);
  }

  @Override
  public boolean tableContainsEntity(final String tableName, final String entityId) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, entityId);
  }

  private TableViewDto getTableSchemaViewByTableName(final String tableName) {
    return databaseView.tableSchemaViews().getStoredFirst(td -> td.name().equals(tableName));
  }
}
