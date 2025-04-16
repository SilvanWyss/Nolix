package ch.nolix.system.majordata.adapter;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.systemapi.majordataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.majordataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.majordataapi.adapterapi.IDataWriter;
import ch.nolix.systemapi.majordataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.majordataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.majordataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiValueEntryDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractDataAdapter implements IDataAdapter {

  private final ICloseController closeController = CloseController.forElement(this);

  private final IDataReader dataReader;

  private final IDataWriter dataWriter;

  protected AbstractDataAdapter(final IDataReader dataReader, final IDataWriter dataWriter) {

    ResourceValidator.assertIsOpen(dataReader);
    ResourceValidator.assertIsOpen(dataWriter);

    this.dataReader = dataReader;
    this.dataWriter = dataWriter;

    createCloseDependencyTo(dataReader);
    createCloseDependencyTo(dataWriter);
  }

  @Override
  public final void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    dataWriter.clearMultiReference(tableName, entityId, multiReferenceColumnName);
  }

  @Override
  public final void clearMultiValue(final String tableName, final String entityId, final String multiValueColumnName) {
    dataWriter.clearMultiValue(tableName, entityId, multiValueColumnName);
  }

  @Override
  public final void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    dataWriter.deleteEntity(tableName, entity);
  }

  @Override
  public final void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {
    dataWriter.deleteMultiReferenceEntry(multiReferenceEntry);
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
  public final void insertEntity(final String tableName, EntityCreationDto entity) {
    dataWriter.insertEntity(tableName, entity);
  }

  @Override
  public final void insertMultiReferenceEntry(MultiReferenceEntryDto multiReferenceEntry) {
    dataWriter.insertMultiReferenceEntry(multiReferenceEntry);
  }

  @Override
  public final void insertMultiValueEntry(MultiValueEntryDto multiValueEntry) {
    dataWriter.insertMultiValueEntry(multiValueEntry);
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
    final String multiValueColumnName) {
    return dataReader.loadMultiValueEntries(tableName, entityId, multiValueColumnName);
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
  public final boolean tableContainsEntity(final String tableName, final String id) {
    return dataReader.tableContainsEntity(tableName, id);
  }

  @Override
  public final boolean tableContainsEntityWithValueInColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return dataReader.tableContainsEntityWithValueInColumn(tableName, columnName, value);
  }

  @Override
  public final boolean tableContainsEntityWithValueInColumnIgnoringEntities(
    final String tableName,
    final String columnName,
    final IContainer<String> entitiesToIgnoreIds,
    final String value) {
    return dataReader.tableContainsEntityWithValueInColumnIgnoringEntities(tableName, columnName, entitiesToIgnoreIds,
      value);
  }

  @Override
  public final void updateEntity(final String tableName, EntityUpdateDto entityUpdate) {
    dataWriter.updateEntity(tableName, entityUpdate);
  }
}
