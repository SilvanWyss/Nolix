package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.dto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  private final ICloseController closeController = CloseController.forElement(this);

  private final InternalDataWriter internalDataWriter;

  private final IContainer<ITableInfo> tableInfos;

  public DataWriter(final IMutableNode<?> nodeDatabase, final IContainer<ITableInfo> tableInfos) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDataWriter = new InternalDataWriter(nodeDatabase);
    this.tableInfos = tableInfos;
  }

  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    internalDataWriter.deleteEntityFromTable(tableName, entity);
  }

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

  @Override
  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
    internalDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    internalDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public int getSaveCount() {
    return internalDataWriter.getSaveCount();
  }

  @Override
  public boolean hasChanges() {
    return internalDataWriter.hasChanges();
  }

  @Override
  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    internalDataWriter.insertEntityIntoTable(getTableInfoByTableName(tableName), newEntity);
  }

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

  @Override
  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDataWriter.insertEntryIntoMultiReference(
      tableInfo,
      entityId,
      tableInfo.getColumnInfoByColumnId(multiReferenceColumnId),
      referencedEntityId);
  }

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

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void reset() {
    internalDataWriter.reset();
  }

  @Override
  public void saveChanges() {
    internalDataWriter.saveChangesAndReset();
  }

  @Override
  public void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    internalDataWriter.updateEntityOnTable(getTableInfoByTableName(tableName), entityUpdate);
  }

  private ITableInfo getTableInfoByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
