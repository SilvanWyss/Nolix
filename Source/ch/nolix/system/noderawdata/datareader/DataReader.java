package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.dto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataReader implements IDataReader {

  private final ICloseController closeController = CloseController.forElement(this);

  private final InternalDataReader internalDataReader;

  private final IContainer<ITableInfo> tableInfos;

  public DataReader(final IMutableNode<?> nodeDatabase, final IContainer<ITableInfo> tableInfos) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDataReader = new InternalDataReader(nodeDatabase);
    this.tableInfos = tableInfos;
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
    final var multiBackReferenceColumnInfo = tableInfo.getColumnInfoByColumnName(multiBackReferenceColumnName);

    return internalDataReader.loadMultiBackReferenceEntries(tableInfo, entityId, multiBackReferenceColumnInfo);
  }

  @Override
  public IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);

    return internalDataReader.loadMultiReferenceEntries(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiReferenceColumnName));
  }

  @Override
  public IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);

    return internalDataReader.loadMultiValueEntries(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiValueColumnName));
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

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(
      tableInfo,
      tableInfo.getColumnInfoByColumnName(columnName),
      value);
  }

  @Override
  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var tableInfo = getTableInfoByTableName(tableName);

    return //
    internalDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableInfo,
      tableInfo.getColumnInfoByColumnName(columnName),
      value,
      entitiesToIgnoreIds);
  }

  @Override
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, id);
  }

  private ITableInfo getTableInfoByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
