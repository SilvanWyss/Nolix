/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import java.util.Iterator;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity}s a {@link MultiBackReference} can
 *            reference back.
 */
public final class MultiBackReference<E extends IEntity>
extends AbstractBaseBackReference
implements IMultiBackReference<E> {
  private static final TableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final DatabaseObjectExaminer DATABASE_OBJECT_EXAMINER = new DatabaseObjectExaminer();

  private static final EntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private boolean loadedAllPersistedBackReferencedEntityIds;

  private final LinkedList<MultiBackReferenceEntry<E>> localEntries = LinkedList.createEmpty();

  private MultiBackReference(
    final ExtendedIterable<String> backReferenceableTableNames,
    final String backReferencedFieldName) {
    super(backReferenceableTableNames, backReferencedFieldName);
  }

  @SafeVarargs
  public static <T extends IEntity> MultiBackReference<T> forBackReferencedFieldNameAndBackReferenceableEntityTypes(
    final String backReferencedFieldName,
    final Class<T>... backReferenceableEntityTypes) {
    final var backReferenceableEntityTypesContainerView = ExtendedIterableView.forArray(backReferenceableEntityTypes);
    final var backReferenceableTableNamesView = //
    backReferenceableEntityTypesContainerView.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new MultiBackReference<>(backReferenceableTableNamesView, backReferencedFieldName);
  }

  public static <T extends IEntity> MultiBackReference<T> forBackReferencedFieldNameAndBackReferenceableEntityTypes(
    final String backReferencedFieldName,
    final ExtendedIterable<Class<? extends T>> backReferenceableEntityTypes) {
    final var backReferenceableTableNamesView = //
    backReferenceableEntityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new MultiBackReference<>(backReferenceableTableNamesView, backReferencedFieldName);
  }

  public static <T extends IEntity> MultiBackReference<T> forBackReferencedFieldNameAndBackReferenceableTableNames(
    final String backReferencedFieldName,
    final ExtendedIterable<String> backReferenceableTableNames) {
    return new MultiBackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<String> getAllBackReferencedEntityIds() {
    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return //
    localEntries
      .getViewOfStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getBackReferencedEntityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<E> getAllStoredBackReferencedEntities() {
    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return //
    localEntries
      .getViewOfStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getStoredBackReferencedEntity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrDeleted);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<BaseReference> getStoredBackReferencedBaseReferences() {
    final ILinkedList<BaseReference> abstractReferences = LinkedList.createEmpty();
    final var backReferencedBaseReferenceName = getBackReferencedFieldName();

    for (final var e : getAllStoredBackReferencedEntities()) {
      final var backReferencedField = //
      (BaseReference) ENTITY_SEARCHER.getStoredFieldByName(e, backReferencedBaseReferenceName);

      abstractReferences.addAtEnd(backReferencedField);
    }

    return abstractReferences;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldType getType() {
    return FieldType.MULTI_BACK_REFERENCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return localEntries.isEmpty()
    && isEmptyWhenDoesNotHaveLocalEntries();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMandatory() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return getAllStoredBackReferencedEntities().iterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean loadedAllPersistedReferencedEntityIds() {
    return loadedAllPersistedBackReferencedEntityIds;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesBackField(final Field field) {
    return //
    field != null
    && field.belongsToEntity()
    && getBackReferencedFieldName().equals(field.getName())
    && referencesBackEntity(field.getStoredParentEntity());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesBackEntity(final IEntity entity) {
    final var entityId = entity.getId();

    return referencesBackEntityWithId(entityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesBackEntityWithId(final String id) {
    return getAllBackReferencedEntityIds().containsEqual(id);
  }

  void internalAddBackReferencedEntity(final IEntity backReferencedEntity) {
    @SuppressWarnings("unchecked")
    final var newEntry = //
    MultiBackReferenceEntry.createNewEntryForMultiBackReferenceAndBackReferencedEntity(
      this,
      (E) backReferencedEntity);

    localEntries.addAtEnd(newEntry);
    setAsEditedAndRunPossibleUpdateAction();
  }

  void deleteEntryByBackReferencedEntityId(final String backReferencedEntityId) {
    final var entry = localEntries.getStoredFirst(e -> e.getBackReferencedEntityId().equals(backReferencedEntityId));

    entry.setDeleted();
    setAsEditedAndRunPossibleUpdateAction();
  }

  private boolean isEmptyWhenDoesNotHaveLocalEntries() {
    return getAllStoredBackReferencedEntities().isEmpty();
  }

  private ExtendedIterable<MultiBackReferenceEntry<E>> loadAllPersistedEntries() {
    final var parentTable = getStoredParentTable();
    final var tableId = parentTable.getId();
    final var tableName = parentTable.getName();
    final var table = new TableIdentification(tableId, tableName);
    final var entityId = getStoredParentEntity().getId();
    final var parentColumn = getStoredParentColumn();
    final var columnId = parentColumn.getId();
    final var columnName = parentColumn.getName();
    final var multiBackReferenceColumn = new ColumnIdentification(columnId, columnName);

    final var multiBackReferenceEntries = //
    getStoredDataAndSchemaAdapter().loadMultiBackReferenceEntries(table, entityId, multiBackReferenceColumn);

    return //
    multiBackReferenceEntries.getViewOf(
      e -> MultiBackReferenceEntryMapper.mapMultiBackReferenceEntryDtoToLoadedMultiBackReferenceEntry(e, this));
  }

  private boolean needsToLoadAllPersistedBackReferencedEntityIds() {
    return //
    !loadedAllPersistedReferencedEntityIds()
    && FIELD_EXAMINER.belongsToLoadedEntity(this);
  }

  private void updateStateLoadingAllPersistedBackReferencedEntityIds() {
    loadedAllPersistedBackReferencedEntityIds = true;

    localEntries.addAtEnd(loadAllPersistedEntries());
  }

  private void updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded() {
    if (needsToLoadAllPersistedBackReferencedEntityIds()) {
      updateStateLoadingAllPersistedBackReferencedEntityIds();
    }
  }
}
