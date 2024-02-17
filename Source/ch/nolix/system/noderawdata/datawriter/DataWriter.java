//package declaration
package ch.nolix.system.noderawdata.datawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class DataWriter implements IDataWriter {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private final InternalDataWriter internalDataWriter;

  //multi-attribute
  private final IContainer<ITableInfo> tableInfos;

  //constructor
  public DataWriter(final IMutableNode<?> nodeDatabase, final IContainer<ITableInfo> tableInfos) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDataWriter = new InternalDataWriter(nodeDatabase);
    this.tableInfos = tableInfos;
  }

  //method
  @Override
  public void deleteEntity(final String tableName, final IEntityHeadDto entity) {
    internalDataWriter.deleteEntityFromTable(tableName, entity);
  }

  //method
  @Override
  public void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var tableInfo = getTableInfoByTableName(tableName);
    final var multiBackReferenceColumnInfo = tableInfo.getColumnInfoByColumnId(multiBackReferenceColumnId);

    internalDataWriter.deleteMultiBackReferenceEntry(
      tableInfo,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);
  }

  //method
  @Override
  public void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDataWriter.deleteEntriesFromMultiReference(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiReferenceColumnName));
  }

  //method
  @Override
  public void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDataWriter.deleteEntryFromMultiReference(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiRefereceColumnName),
      referencedEntityId);
  }

  //method
  @Override
  public void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDataWriter.deleteEntriesFromMultiValue(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiValueColumnName));
  }

  //method
  @Override
  public void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDataWriter.deleteEntryFromMultiValue(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiValueColumnName),
      entry);
  }

  //method
  @Override
  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
    internalDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  //method
  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    internalDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  //method
  @Override
  public int getSaveCount() {
    return internalDataWriter.getSaveCount();
  }

  //method
  @Override
  public boolean hasChanges() {
    return internalDataWriter.hasChanges();
  }

  //method
  @Override
  public void insertEntity(final String tableName, final INewEntityDto newEntity) {
    internalDataWriter.insertEntityIntoTable(getTableInfoByTableName(tableName), newEntity);
  }

  //method
  @Override
  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var tableInfo = getTableInfoByTableName(tableName);
    final var multiBackReferenceColumnInfo = tableInfo.getColumnInfoByColumnId(multiBackReferenceColumnId);

    internalDataWriter.insertEntryIntoMultiBackReference(
      tableInfo,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);
  }

  //method
  @Override
  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName,
    final String referencedEntityId) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDataWriter.insertEntryIntoMultiReference(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiReferenceColumnName),
      referencedEntityId);
  }

  //method
  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDataWriter.insertEntryIntoMultiValue(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnName(multiValueColumnName),
      entry);
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  @Override
  public void reset() {
    internalDataWriter.reset();
  }

  //method
  @Override
  public void saveChanges() {
    internalDataWriter.saveChangesAndReset();
  }

  //method
  @Override
  public void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    internalDataWriter.setEntityAsUpdated(tableName, entity);
  }

  //method
  @Override
  public void updateEntity(final String tableName, final IEntityUpdateDto entityUpdate) {
    internalDataWriter.updateEntityOnTable(getTableInfoByTableName(tableName), entityUpdate);
  }

  //method
  private ITableInfo getTableInfoByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
