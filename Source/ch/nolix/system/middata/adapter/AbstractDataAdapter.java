package ch.nolix.system.middata.adapter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.ICloseController;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.middataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.middataapi.adapterapi.IDataWriter;
import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiValueEntryDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractDataAdapter implements IDataAdapter {

  private final ICloseController closeController = CloseController.forElement(this);

  private final IDataReader dataReader;

  private final IDataWriter dataWriter;

  protected AbstractDataAdapter(final IDataReader dataReader, final IDataWriter dataWriter) {

    Validator.assertThat(dataReader).thatIsNamed(IDataReader.class).isNotNull();
    Validator.assertThat(dataWriter).thatIsNamed(IDataWriter.class).isNotNull();

    this.dataReader = dataReader;
    this.dataWriter = dataWriter;

    getStoredCloseController().createCloseDependencyTo(dataReader);

    if (dataReader != dataWriter) {
      getStoredCloseController().createCloseDependencyTo(dataWriter);
    }
  }

  @Override
  public final void createCloseDependencyTo(final GroupCloseable element) {
    IDataAdapter.super.createCloseDependencyTo(element);
  }

  @Override
  public final void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    dataWriter.deleteEntity(tableName, entity);
  }

  @Override
  public final void deleteMultiBackReferenceEntry(
    final MultiBackReferenceEntryDeletionDto multiBackReferenceEntry) {
    dataWriter.deleteMultiBackReferenceEntry(multiBackReferenceEntry);
  }

  @Override
  public final void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    dataWriter.clearMultiReference(tableName, entityId, multiReferenceColumnName);
  }

  @Override
  public final void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {
    dataWriter.deleteMultiReferenceEntry(multiReferenceEntry);
  }

  @Override
  public final void clearMultiValue(
    final String tableName,
    final String entityId,
    final String multiFieldColumn) {
    dataWriter.clearMultiValue(tableName, entityId, multiFieldColumn);
  }

  @Override
  public final void deleteMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    dataWriter.deleteMultiValueEntry(multiValueEntry);
  }

  @Override
  public final void expectSchemaTimestamp(final ITime schemaTimestamp) {
    dataWriter.expectSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public final void expectTableContainsEntity(final String tableName, final String entityId) {
    dataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public final String getDatabaseName() {
    return dataReader.getDatabaseName();
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
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final boolean hasChanges() {
    return dataWriter.hasChanges();
  }

  @Override
  public final void insertMultiBackReferenceEntry(final MultiBackReferenceEntryDto multiBackReferenceEntry) {
    dataWriter.insertMultiBackReferenceEntry(multiBackReferenceEntry);
  }

  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {
    dataWriter.insertMultiReferenceEntry(multiReferenceEntry);
  }

  @Override
  public final void insertMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    dataWriter.insertMultiValueEntry(multiValueEntry);
  }

  @Override
  public final void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    dataWriter.insertEntity(tableName, newEntity);
  }

  @Override
  public final IContainer<String> loadMultiBackReferenceBackReferencedEntityIds(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {
    return dataReader.loadMultiBackReferenceBackReferencedEntityIds(tableName, entityId, multiBackReferenceColumnName);
  }

  @Override
  public final IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return dataReader.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  @Override
  public final IContainer<Object> loadMultiValueValues(
    final String tableName,
    final String entityId,
    final String multiFieldColumnName) {
    return dataReader.loadMultiValueValues(tableName, entityId, multiFieldColumnName);
  }

  @Override
  public final IContainer<EntityLoadingDto> loadEntities(final String tableName) {
    return dataReader.loadEntities(tableName);
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
  public final boolean tableContainsEntityWithValueAtColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return dataReader.tableContainsEntityWithValueAtColumn(tableName, columnName, value);
  }

  @Override
  public final boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    dataReader.tableContainsEntityWithValueAtColumnIgnoringEntities(
      tableName,
      columnName,
      value,
      entitiesToIgnoreIds);
  }

  @Override
  public final boolean tableContainsEntity(final String tableName, final String entityId) {
    return dataReader.tableContainsEntity(tableName, entityId);
  }

  @Override
  public final void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    dataWriter.updateEntity(tableName, entityUpdate);
  }
}
