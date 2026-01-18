/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.middata.adapter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.middata.adapter.IDataAdapter;
import ch.nolix.systemapi.middata.adapter.IDataReader;
import ch.nolix.systemapi.middata.adapter.IDataWriter;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiValueEntryDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.time.moment.ITime;

public abstract class AbstractDataAdapter implements IDataAdapter {
  private final ICloseController closeController = CloseController.forElement(this);

  private final IDataReader dataReader;

  private final IDataWriter dataWriter;

  protected AbstractDataAdapter(final IDataReader dataReader, final IDataWriter dataWriter) {
    Validator.assertThat(dataReader).thatIsNamed(IDataReader.class).isNotNull();
    Validator.assertThat(dataWriter).thatIsNamed(IDataWriter.class).isNotNull();

    this.dataReader = dataReader;
    this.dataWriter = dataWriter;

    createCloseDependencyTo(dataReader);

    if (dataReader != dataWriter) {
      createCloseDependencyTo(dataWriter);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    dataWriter.clearMultiReference(tableName, entityId, multiReferenceColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clearMultiValue(
    final String tableName,
    final String entityId,
    final String multiFieldColumn) {
    dataWriter.clearMultiValue(tableName, entityId, multiFieldColumn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void createCloseDependencyTo(final GroupCloseable element) {
    IDataAdapter.super.createCloseDependencyTo(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    dataWriter.deleteEntity(tableName, entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void deleteMultiBackReferenceEntry(
    final MultiBackReferenceEntryDeletionDto multiBackReferenceEntry) {
    dataWriter.deleteMultiBackReferenceEntry(multiBackReferenceEntry);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {
    dataWriter.deleteMultiReferenceEntry(multiReferenceEntry);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void deleteMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    dataWriter.deleteMultiValueEntry(multiValueEntry);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void expectSchemaTimestamp(final ITime schemaTimestamp) {
    dataWriter.expectSchemaTimestamp(schemaTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void expectTableContainsEntity(final String tableName, final String entityId) {
    dataWriter.expectTableContainsEntity(tableName, entityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDatabaseName() {
    return dataReader.getDatabaseName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getEntityCount(final String tableName) {
    return dataReader.getEntityCount(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getSaveCount() {
    return dataWriter.getSaveCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ITime getSchemaTimestamp() {
    return dataReader.getSchemaTimestamp();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasChanges() {
    return dataWriter.hasChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<EntityLoadingDto> loadEntities(final String tableName) {
    return dataReader.loadEntities(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final EntityLoadingDto loadEntity(final String tableName, final String id) {
    return dataReader.loadEntity(tableName, id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    dataWriter.insertEntity(tableName, newEntity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void insertMultiBackReferenceEntry(final MultiBackReferenceEntryDto multiBackReferenceEntry) {
    dataWriter.insertMultiBackReferenceEntry(multiBackReferenceEntry);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {
    dataWriter.insertMultiReferenceEntry(multiReferenceEntry);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void insertMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    dataWriter.insertMultiValueEntry(multiValueEntry);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> loadMultiBackReferenceBackReferencedEntityIds(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {
    return dataReader.loadMultiBackReferenceBackReferencedEntityIds(tableName, entityId, multiBackReferenceColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<MultiBackReferenceEntryDto> loadMultiBackReferenceEntries(
    final TableIdentification table,
    final String entityId,
    final ColumnIdentification multiBackReferenceColumn) {
    return dataReader.loadMultiBackReferenceEntries(table, entityId, multiBackReferenceColumn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return dataReader.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<Object> loadMultiValueValues(
    final String tableName,
    final String entityId,
    final String multiFieldColumnName) {
    return dataReader.loadMultiValueValues(tableName, entityId, multiFieldColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void reset() {
    dataWriter.reset();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void saveChanges() {
    dataWriter.saveChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean tableContainsEntity(final String tableName, final String entityId) {
    return dataReader.tableContainsEntity(tableName, entityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean tableContainsEntityWithValueAtColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return dataReader.tableContainsEntityWithValueAtColumn(tableName, columnName, value);
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public final void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    dataWriter.updateEntity(tableName, entityUpdate);
  }
}
