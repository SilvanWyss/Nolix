package ch.nolix.system.rawdata.adapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.dto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityUpdateDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractDataAdapter implements IDataAdapter {

  private final ICloseController closeController = CloseController.forElement(this);

  private final IDataReader dataReader;

  private final IDataWriter dataWriter;

  protected AbstractDataAdapter(final IDataReader dataReader, final IDataWriter dataWriter) {

    GlobalValidator.assertThat(dataReader).thatIsNamed(IDataReader.class).isNotNull();
    GlobalValidator.assertThat(dataWriter).thatIsNamed(IDataWriter.class).isNotNull();

    this.dataReader = dataReader;
    this.dataWriter = dataWriter;

    getStoredCloseController().createCloseDependencyTo(dataReader);
    getStoredCloseController().createCloseDependencyTo(dataWriter);
  }

  @Override
  public final void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    dataWriter.deleteEntity(tableName, entity);
  }

  @Override
  public final void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    dataWriter.deleteMultiBackReferenceEntry(tableName, entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public final void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    dataWriter.deleteMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  @Override
  public final void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {
    dataWriter.deleteMultiReferenceEntry(tableName, entityId, multiRefereceColumnName, referencedEntityId);
  }

  @Override
  public final void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiFieldColumn) {
    dataWriter.deleteMultiValueEntries(tableName, entityId, multiFieldColumn);
  }

  @Override
  public final void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiFieldColumn,
    final String entry) {
    dataWriter.deleteMultiValueEntry(tableName, entityId, multiFieldColumn, entry);
  }

  @Override
  public final void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    dataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public final void expectTableContainsEntity(final String tableName, final String entityId) {
    dataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final int getSaveCount() {
    return dataWriter.getSaveCount();
  }

  @Override
  public final ITime getSchemaTimestamp() {
    return dataReader.getSchemaTimestamp();
  }

  @Override
  public final boolean hasChanges() {
    return dataWriter.hasChanges();
  }

  @Override
  public final void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    dataWriter.insertMultiReferenceEntry(tableName, entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public final void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    dataWriter.insertMultiReferenceEntry(tableName, entityId, multiReferenceColumnId, referencedEntityId);
  }

  @Override
  public final void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiFieldColumn,
    final String entry) {
    dataWriter.insertMultiValueEntry(tableName, entityId, multiFieldColumn, entry);
  }

  @Override
  public final void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    dataWriter.insertEntity(tableName, newEntity);
  }

  @Override
  public final IContainer<String> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {
    return dataReader.loadMultiBackReferenceEntries(tableName, entityId, multiBackReferenceColumnName);
  }

  @Override
  public final IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return dataReader.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  @Override
  public final IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiFieldColumnName) {
    return dataReader.loadMultiValueEntries(tableName, entityId, multiFieldColumnName);
  }

  @Override
  public final IContainer<EntityLoadingDto> loadEntitiesOfTable(final String tableName) {
    return dataReader.loadEntitiesOfTable(tableName);
  }

  @Override
  public final EntityLoadingDto loadEntity(final String tableName, final String id) {
    return dataReader.loadEntity(tableName, id);
  }

  @Override
  public final void noteClose() {
    //Does nothing.
  }

  @Override
  public final void reset() {
    dataWriter.reset();
  }

  @Override
  public final void saveChanges() {
    dataWriter.saveChanges();
  }

  @Override
  public final boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return dataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnName, value);
  }

  @Override
  public final boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    dataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnName,
      value,
      entitiesToIgnoreIds);
  }

  @Override
  public final boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return dataReader.tableContainsEntityWithGivenId(tableName, id);
  }

  @Override
  public final void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    dataWriter.updateEntity(tableName, entityUpdate);
  }
}
