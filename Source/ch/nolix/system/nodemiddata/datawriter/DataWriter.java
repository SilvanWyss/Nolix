/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.datawriter;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.midschemainfo.modelsearcher.DatabaseInfoSearcherForDatabaseView;
import ch.nolix.system.nodemiddata.nodemapper.EntityIndexNodeMapper;
import ch.nolix.system.nodemiddata.nodemapper.EntityNodeMapper;
import ch.nolix.systemapi.middata.adapter.IDataWriter;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiValueEntryDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.midschemainfo.modelsearcher.IDatabaseInfoSearcherForDatabaseInfo;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class DataWriter implements IDataWriter {
  public static final int INITIAL_ENTITY_SAVE_STAMP = 0;

  private static final EntityNodeMapper ENTITY_NODE_MAPPER = new EntityNodeMapper();

  private static final EntityIndexNodeMapper ENTITY_INDEXES_NODE_MAPPER = new EntityIndexNodeMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final IDatabaseInfoSearcherForDatabaseInfo databaseInfoSearcherForDatabaseInfo;

  private final ExecutiveDataWriter executiveDataWriter;

  private DataWriter(final IMutableNode<?> nodeDatabase, final DatabaseInfoDto databaseView) {
    databaseInfoSearcherForDatabaseInfo = DatabaseInfoSearcherForDatabaseView.forDatabaseView(databaseView);
    executiveDataWriter = ExecutiveDataWriter.forNodeDatabase(nodeDatabase);
  }

  public static DataWriter forNodeDatabaseAndDatabaseView(
    final IMutableNode<?> nodeDatabase,
    final DatabaseInfoDto databaseView) {
    return new DataWriter(nodeDatabase, databaseView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    final var multiReferenceColumnView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);

    final var multiReferencedColumnOneBasedOrdinalIndex = multiReferenceColumnView.oneBasedOrdinalIndex();

    executiveDataWriter.clearMultiReference(tableName, entityId, multiReferencedColumnOneBasedOrdinalIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    final var multiValueColumnView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);

    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();

    executiveDataWriter.clearMultiValue(tableName, entityId, multiValueColumnOneBasedOrdinalIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    final var entityId = entity.id();
    final var entitySaveStamp = entity.saveStamp();

    executiveDataWriter.deleteEntity(tableName, entityId, entitySaveStamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteMultiBackReferenceEntry(final MultiBackReferenceEntryDeletionDto multiBackReferenceEntry) {
    final var tableName = multiBackReferenceEntry.tableName();
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnId = multiBackReferenceEntry.multiBackReferenceColumnId();

    final var multiBackReferenceColumnView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnId(tableName, multiBackReferenceColumnId);

    final var multiBackReferenceColumnOneBasedOrdinalIndex = multiBackReferenceColumnView.oneBasedOrdinalIndex();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();

    executiveDataWriter.deleteMultiBackReferenceEntry(
      tableName,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      backReferencedEntityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {
    final var tableName = multiReferenceEntry.tableName();
    final var entityId = multiReferenceEntry.entityId();
    final var multiReferenceColumnId = multiReferenceEntry.multiReferenceColumnId();

    final var multiReferenceColumnNameView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnId(tableName, multiReferenceColumnId);

    final var multiReferencedColumnOneBasedOrdinalIndex = multiReferenceColumnNameView.oneBasedOrdinalIndex();
    final var referencedEntityId = multiReferenceEntry.referencedEntityId();

    executiveDataWriter.deleteMultiReferenceEntry(
      tableName,
      entityId,
      multiReferencedColumnOneBasedOrdinalIndex,
      referencedEntityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    final var tableName = multiValueEntry.tableName();
    final var entityId = multiValueEntry.entityId();
    final var multiValueColumnId = multiValueEntry.multiValueColumnId();

    final var multiValueColumnView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnId(tableName, multiValueColumnId);

    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();
    final var value = multiValueEntry.value();

    executiveDataWriter.deleteMultiValueEntry(tableName, entityId, multiValueColumnOneBasedOrdinalIndex, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void expectSchemaTimestamp(ITime schemaTimestamp) {
    executiveDataWriter.expectSchemaTimestamp(schemaTimestamp);
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
  public int getSaveCount() {
    return executiveDataWriter.getSaveCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChanges() {
    return executiveDataWriter.hasUpdates();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertEntity(final String tableName, final EntityCreationDto entity) {
    final var tableView = databaseInfoSearcherForDatabaseInfo.getTableViewByTableName(tableName);
    final var tableId = tableView.id();
    final var entityId = entity.id();
    final var entityIndexNode = ENTITY_INDEXES_NODE_MAPPER.mapEntityCreationDtoToEntityIndexNode(entity, tableId);
    final var saveStamp = INITIAL_ENTITY_SAVE_STAMP;
    final var entityNode = ENTITY_NODE_MAPPER.mapEntityCreationDtoToEntityNode(entity, tableView, saveStamp);

    executiveDataWriter.insertEntity(tableName, entityId, entityIndexNode, entityNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertMultiBackReferenceEntry(final MultiBackReferenceEntryDto multiBackReferenceEntry) {
    final var tableName = multiBackReferenceEntry.tableName();
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnId = multiBackReferenceEntry.multiBackReferenceColumnId();

    final var multiBackReferenceColumnView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnId(tableName, multiBackReferenceColumnId);

    final var multiBackReferenceColumnOneBasedOrdinalIndex = multiBackReferenceColumnView.oneBasedOrdinalIndex();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();
    final var backReferencedEntityTableId = multiBackReferenceEntry.backReferencedEntityTableId();

    final var multiBackReferenceEntryNode = //
    ImmutableNode.withChildNodes(backReferencedEntityId, backReferencedEntityTableId);

    executiveDataWriter.insertMultiBackReferenceEntry(
      tableName,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      multiBackReferenceEntryNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {
    final var tableName = multiReferenceEntry.tableName();
    final var entityId = multiReferenceEntry.entityId();
    final var multiReferenceColumnId = multiReferenceEntry.multiReferenceColumnId();

    final var multiReferenceColumnView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnId(tableName, multiReferenceColumnId);

    final var multiReferenceColumnOneBasedOrdinalIndex = multiReferenceColumnView.oneBasedOrdinalIndex();

    final var multiReferenceEntryNode = //
    ImmutableNode.withChildNodes(
      multiReferenceEntry.referencedEntityId(),
      multiReferenceEntry.referencedEntityTableId());

    executiveDataWriter.insertMultiReferenceEntry(
      tableName,
      entityId,
      multiReferenceColumnOneBasedOrdinalIndex,
      multiReferenceEntryNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insertMultiValueEntry(final MultiValueEntryDto multiValueEntry) {
    final var tableName = multiValueEntry.tableName();
    final var entityId = multiValueEntry.entityId();
    final var multiValueColumnId = multiValueEntry.multiValueColumnId();

    final var multiValueColumnView = //
    databaseInfoSearcherForDatabaseInfo.getColumnViewByTableNameAndColumnId(tableName, multiValueColumnId);

    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();
    final var value = multiValueEntry.value();

    executiveDataWriter.insertMultiValueEntry(tableName, entityId, multiValueColumnOneBasedOrdinalIndex, value);
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
    final var tableView = databaseInfoSearcherForDatabaseInfo.getTableViewByTableName(tableName);

    executiveDataWriter.updateEntity(entityUpdate, tableView);
  }
}
