package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

public final class MultiBackReference<E extends IEntity>
extends AbstractBaseBackReference<E>
implements IMultiBackReference<E> {

  private static final IDatabaseObjectExaminer DATABASE_OBJECT_EXAMINER = new DatabaseObjectExaminer();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private boolean loadedAllPersistedBackReferencedEntityIds;

  private final ILinkedList<MultiBackReferenceEntry<E>> localEntries = LinkedList.createEmpty();

  private MultiBackReference(final String backReferencedTableName, final String backReferencedBaseReferenceName) {
    super(backReferencedTableName, backReferencedBaseReferenceName);
  }

  public static <E2 extends AbstractEntity> MultiBackReference<E2> forBackReferencedEntityTypeAndBaseReference(
    final Class<E2> backReferencedEntityType,
    final String backReferencedBaseReferenceName) {

    final var entityTypeName = backReferencedEntityType.getSimpleName();

    return forBackReferencedTableAndBaseReference(entityTypeName, backReferencedBaseReferenceName);
  }

  public static <E2 extends AbstractEntity> MultiBackReference<E2> forBackReferencedTableAndBaseReference(
    final String backReferencedTableName,
    final String backReferencedBaseReference) {
    return new MultiBackReference<>(backReferencedTableName, backReferencedBaseReference);
  }

  @Override
  public IContainer<String> getAllBackReferencedEntityIds() {

    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getBackReferencedEntityId);
  }

  @Override
  public IContainer<E> getAllStoredBackReferencedEntities() {

    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getStoredBackReferencedEntity);
  }

  @Override
  public IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrDeleted);
  }

  @Override
  public IContainer<IBaseReference<IEntity>> getStoredBaseReferencesWhoAreBackReferencedFromThis() {

    final ILinkedList<IBaseReference<IEntity>> abstractReferences = LinkedList.createEmpty();
    final var backReferencedBaseReferenceName = getBackReferencedFieldName();

    for (final var e : getAllStoredBackReferencedEntities()) {

      @SuppressWarnings("unchecked")
      final var backReferencedField = //
      (IBaseReference<IEntity>) ENTITY_SEARCHER.getStoredFieldByName(e, backReferencedBaseReferenceName);

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
  public boolean loadedAllPersistedReferencedEntityIds() {
    return loadedAllPersistedBackReferencedEntityIds;
  }

  @Override
  public boolean referencesBackEntity(final IEntity entity) {

    final var backReferencedBaseReferenceName = getBackReferencedFieldName();

    for (final var p : entity.internalGetStoredFields()) {
      if (p.hasName(backReferencedBaseReferenceName)) {
        return p.referencesEntity(entity);
      }
    }

    return false;
  }

  @Override
  public boolean referencesBackEntityWithId(final String id) {

    final var entity = getStoredBackReferencedTable().getStoredEntityById(id);

    return referencesBackEntity(entity);
  }

  void internalAddBackReferencedEntityId(final String backReferencedEntityId) {

    final var newEntry = MultiBackReferenceEntry.newEntryForMultiBackReferenceAndReferencedEntityId(
      this,
      backReferencedEntityId);

    localEntries.addAtEnd(newEntry);
  }

  void internalDeleteBackReferencedEntityId(final String backReferencedEntityId) {

    final var entry = localEntries.getStoredFirst(e -> e.getBackReferencedEntityId().equals(backReferencedEntityId));

    entry.internalDelete();
  }

  private boolean isEmptyWhenDoesNotHaveLocalEntries() {
    return getAllStoredBackReferencedEntities().isEmpty();
  }

  private IContainer<MultiBackReferenceEntry<E>> loadAllPersistedBackReferencedEntityIds() {

    final var entity = getStoredParentEntity();

    return //
    getStoredDataAndSchemaAdapter().loadMultiBackReferenceBackReferencedEntityIds(
      entity.getParentTableName(),
      entity.getId(),
      getName())
      .to(e -> MultiBackReferenceEntry.loadedEntryForMultiBackReferenceAndReferencedEntityId(this, e));
  }

  private boolean needsToLoadAllPersistedBackReferencedEntityIds() {
    return //
    !loadedAllPersistedReferencedEntityIds()
    && FIELD_EXAMINER.belongsToLoadedEntity(this);
  }

  private void updateStateLoadingAllPersistedBackReferencedEntityIds() {

    loadedAllPersistedBackReferencedEntityIds = true;

    localEntries.addAtEnd(loadAllPersistedBackReferencedEntityIds());
  }

  private void updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded() {
    if (needsToLoadAllPersistedBackReferencedEntityIds()) {
      updateStateLoadingAllPersistedBackReferencedEntityIds();
    }
  }
}
