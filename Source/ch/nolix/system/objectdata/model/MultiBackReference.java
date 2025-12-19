package ch.nolix.system.objectdata.model;

import java.util.Iterator;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReference<E extends IEntity>
extends AbstractBaseBackReference
implements IMultiBackReference<E> {
  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IDatabaseObjectExaminer DATABASE_OBJECT_EXAMINER = new DatabaseObjectExaminer();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private boolean loadedAllPersistedBackReferencedEntityIds;

  private final ILinkedList<MultiBackReferenceEntry<E>> localEntries = LinkedList.createEmpty();

  private MultiBackReference(
    final IContainer<String> backReferenceableTableNames,
    final String backReferencedFieldName) {
    super(backReferenceableTableNames, backReferencedFieldName);
  }

  @SafeVarargs
  public static <T extends IEntity> MultiBackReference<T> forBackReferencedFieldNameAndBackReferenceableEntityTypes(
    final String backReferencedFieldName,
    final Class<T>... backReferenceableEntityTypes) {
    final var backReferenceableEntityTypesContainerView = ContainerView.forArray(backReferenceableEntityTypes);
    final var backReferenceableTableNamesView = //
    backReferenceableEntityTypesContainerView.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new MultiBackReference<>(backReferenceableTableNamesView, backReferencedFieldName);
  }

  public static <T extends IEntity> MultiBackReference<T> forBackReferencedFieldNameAndBackReferenceableEntityTypes(
    final String backReferencedFieldName,
    final IContainer<Class<? extends T>> backReferenceableEntityTypes) {
    final var backReferenceableTableNamesView = //
    backReferenceableEntityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new MultiBackReference<>(backReferenceableTableNamesView, backReferencedFieldName);
  }

  public static <T extends IEntity> MultiBackReference<T> forBackReferencedFieldNameAndBackReferenceableTableNames(
    final String backReferencedFieldName,
    final IContainer<String> backReferenceableTableNames) {
    return new MultiBackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  @Override
  public IContainer<String> getAllBackReferencedEntityIds() {
    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return //
    localEntries
      .getViewOfStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getBackReferencedEntityId);
  }

  @Override
  public IContainer<E> getAllStoredBackReferencedEntities() {
    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return //
    localEntries
      .getViewOfStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getStoredBackReferencedEntity);
  }

  @Override
  public IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrDeleted);
  }

  @Override
  public IContainer<IBaseReference> getStoredBackReferencedBaseReferences() {
    final ILinkedList<IBaseReference> abstractReferences = LinkedList.createEmpty();
    final var backReferencedBaseReferenceName = getBackReferencedFieldName();

    for (final var e : getAllStoredBackReferencedEntities()) {
      final var backReferencedField = //
      (IBaseReference) ENTITY_SEARCHER.getStoredFieldByName(e, backReferencedBaseReferenceName);

      abstractReferences.addAtEnd(backReferencedField);
    }

    return abstractReferences;
  }

  @Override
  public FieldType getType() {
    return FieldType.MULTI_BACK_REFERENCE;
  }

  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {
    //Does nothing.
  }

  @Override
  public boolean isEmpty() {
    return localEntries.isEmpty()
    && isEmptyWhenDoesNotHaveLocalEntries();
  }

  @Override
  public boolean isMandatory() {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return getAllStoredBackReferencedEntities().iterator();
  }

  @Override
  public boolean loadedAllPersistedReferencedEntityIds() {
    return loadedAllPersistedBackReferencedEntityIds;
  }

  @Override
  public boolean referencesBackField(final IField field) {
    return //
    field != null
    && field.belongsToEntity()
    && getBackReferencedFieldName().equals(field.getName())
    && referencesBackEntity(field.getStoredParentEntity());
  }

  @Override
  public boolean referencesBackEntity(final IEntity entity) {
    final var entityId = entity.getId();

    return referencesBackEntityWithId(entityId);
  }

  @Override
  public boolean referencesBackEntityWithId(final String id) {
    return getAllBackReferencedEntityIds().containsEqualing(id);
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

  private IContainer<MultiBackReferenceEntry<E>> loadAllPersistedEntries() {
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
