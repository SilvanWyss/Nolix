package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.model.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataReader implements IDataReader {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final InternalDataReader internalDataReader;

  private final IContainer<TableViewDto> tableViews;

  public DataReader(final IMutableNode<?> nodeDatabase, final IContainer<TableViewDto> tableViews) {

    GlobalValidator.assertThat(tableViews).thatIsNamed("table definitions").isNotNull();
    GlobalValidator.assertThat(tableViews).thatIsNamed("table definitions").isNotNull();

    internalDataReader = new InternalDataReader(nodeDatabase);
    this.tableViews = tableViews;
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public ITime getSchemaTimestamp() {
    return internalDataReader.getSchemaTimestamp();
  }

  @Override
  public IContainer<String> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);

    final var multiBackReferenceColumnInfo = //
    TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiBackReferenceColumnName);

    return internalDataReader.loadMultiBackReferenceEntries(tableInfo, entityId, multiBackReferenceColumnInfo);
  }

  @Override
  public IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiReferenceColumnName);

    return internalDataReader.loadMultiReferenceEntries(tableInfo, entityId, columnView);
  }

  @Override
  public IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    return internalDataReader.loadMultiValueEntries(tableInfo, entityId, columnView);
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

    final var tableInfo = getTableInfoByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableInfo, columnView, value);
  }

  @Override
  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var tableInfo = getTableInfoByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, columnName);

    return //
    internalDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableInfo,
      columnView,
      value,
      entitiesToIgnoreIds);
  }

  @Override
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, id);
  }

  private TableViewDto getTableInfoByTableName(final String tableName) {
    return tableViews.getStoredFirst(td -> td.name().equals(tableName));
  }
}
