//package declaration
package ch.nolix.system.noderawdata.datareader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class DataReader implements IDataReader {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private final InternalDataReader internalDataReader;

  //multi-attribute
  private final IContainer<ITableInfo> tableInfos;

  //constructor
  public DataReader(final IMutableNode<?> databaseNode, final IContainer<ITableInfo> tableInfos) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDataReader = new InternalDataReader(databaseNode);
    this.tableInfos = tableInfos;
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

  //method
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
  public boolean multiValueIsEmpty(final String tableName, final String entityId, final String multiValueColumnId) {
    //TODO: Implement.
    return false;
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

    final var tableInfo = getTableInfoByTableName(tableName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(
      getTableInfoByTableName(tableName),
      tableInfo.getColumnInfoByColumnName(columnName),
      value);
  }

  //method
  @Override
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, id);
  }

  //method
  private ITableInfo getTableInfoByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
