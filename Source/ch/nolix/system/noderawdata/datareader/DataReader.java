package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataReader implements IDataReader {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseSchemaViewDto databaseSchemaView;

  private final InternalDataReader internalDataReader;

  public DataReader(final IMutableNode<?> nodeDatabase, final DatabaseSchemaViewDto databaseSchemaView) {

    Validator.assertThat(databaseSchemaView).thatIsNamed(DatabaseSchemaViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    this.internalDataReader = new InternalDataReader(nodeDatabase);
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
  public IContainer<String> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);

    final var multiBackReferenceColumnInfo = //
    TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiBackReferenceColumnName);

    return internalDataReader.loadMultiBackReferenceEntries(tableInfo, entityId, multiBackReferenceColumnInfo);
  }

  @Override
  public IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiReferenceColumnName);

    return internalDataReader.loadMultiReferenceEntries(tableInfo, entityId, columnView);
  }

  @Override
  public IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    return internalDataReader.loadMultiValueEntries(tableInfo, entityId, columnView);
  }

  @Override
  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final String tableName) {
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
  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableInfo, columnView, value);
  }

  @Override
  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
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

  private TableSchemaViewDto getTableSchemaViewByTableName(final String tableName) {
    return databaseSchemaView.tableSchemaViews().getStoredFirst(td -> td.name().equals(tableName));
  }
}
