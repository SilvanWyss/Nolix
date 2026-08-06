/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.datawriter;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.midschemainfo.modelsearcher.DatabaseInfoSearcher;
import ch.nolix.systemapi.middata.adapter.IDataWriter;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiValueEntryDto;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class DataWriter implements IDataWriter {
  private static final DatabaseInfoSearcher DATABASE_VIEW_SEARCHER = new DatabaseInfoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseInfoDto databaseView;

  private final ExecutiveDataWriter executiveDataWriter;

  private DataWriter(
    final String databaseName,
    final DatabaseInfoDto databaseView,
    final ISqlConnection sqlConnection) {
    Validator.assertThat(databaseView).thatIsNamed("database view").isNotNull();

    this.databaseView = databaseView;
    this.executiveDataWriter = ExecutiveDataWriter.withDatabaseNameAndSqlConnection(databaseName, sqlConnection);

    createCloseDependencyTo(sqlConnection);
  }

  public static DataWriter forDatabaseNameAndDatabaseViewAndSqlConnection(
    final String databaseName,
    final DatabaseInfoDto databaseView,
    final ISqlConnection sqlConnection) {
    return new DataWriter(databaseName, databaseView, sqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    final var multiReferenceColumnView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);
    final var multiReferenceColumnId = multiReferenceColumnView.id();

    executiveDataWriter.deleteEntriesFromMultiReference(entityId, multiReferenceColumnId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    final var multiValueColumnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);
    final var multiValueColumnId = multiValueColumnView.id();

    executiveDataWriter.deleteEntriesFromMultiValue(entityId, multiValueColumnId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    executiveDataWriter.deleteEntity(tableName, entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteMultiBackReferenceEntry(final MultiBackReferenceEntryDeletionDto multiBackReferenceEntry) {
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnId = multiBackReferenceEntry.multiBackReferenceColumnId();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();

    executiveDataWriter.deleteMultiBackReferenceEntry(entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {
    final var entityId = multiReferenceEntry.entityId();
    final var multiReferenceColumnId = multiReferenceEntry.multiReferenceColumnId();
    final var referencedEntityId = multiReferenceEntry.referencedEntityId();

    executiveDataWriter.deleteEntryFromMultiReference(entityId, multiReferenceColumnId, referencedEntityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    final var entityId = multiValueEntry.entityId();
    final var multiValueColumnId = multiValueEntry.multiValueColumnId();
    final var value = multiValueEntry.value();

    executiveDataWriter.deleteMultiValueEntry(entityId, multiValueColumnId, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void expectSchemaTimestamp(final ITime schemaTimestamp) {
    executiveDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    executiveDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSaveCount() {
    return executiveDataWriter.getSaveCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChanges() {
    return executiveDataWriter.hasChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    executiveDataWriter.insertEntity(tableName, newEntity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertMultiBackReferenceEntry(final MultiBackReferenceEntryDto multiBackReferenceEntry) {
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnId = multiBackReferenceEntry.multiBackReferenceColumnId();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();
    final var backReferencedEntityTableId = multiBackReferenceEntry.backReferencedEntityTableId();

    executiveDataWriter.insertEntryIntoMultiBackReference(
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId,
      backReferencedEntityTableId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {
    final var entityId = multiReferenceEntry.entityId();
    final var multiReferenceColumnId = multiReferenceEntry.multiReferenceColumnId();
    final var referencedEntityId = multiReferenceEntry.referencedEntityId();
    final var referencedEntityTableId = multiReferenceEntry.referencedEntityId();

    executiveDataWriter.insertMultiReferenceEntry(
      entityId,
      multiReferenceColumnId,
      referencedEntityId,
      referencedEntityTableId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    final var entityId = multiValueEntry.entityId();
    final var multiValueColumnId = multiValueEntry.multiValueColumnId();
    final var value = multiValueEntry.value();

    executiveDataWriter.insertEntryIntoMultiValue(entityId, multiValueColumnId, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    executiveDataWriter.reset();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveChanges() {
    executiveDataWriter.saveChangesAndReset();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    executiveDataWriter.updateEntityOnTable(tableName, entityUpdate);
  }

  private ColumnInfoDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnName(databaseView, tableName, columnName);
  }
}
