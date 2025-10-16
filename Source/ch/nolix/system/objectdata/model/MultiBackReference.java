package ch.nolix.system.objectdata.model;

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
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

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

  public static <E2 extends IEntity> MultiBackReference<E2> forBackReferenceableEntityTypesAndBackReferencedFieldName(
    final IContainer<Class<? extends E2>> backReferenceableEntityTypes,
    final String backReferencedFieldName) {
    final var backReferenceableTableNames = //
    backReferenceableEntityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new MultiBackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  public static <E2 extends IEntity> MultiBackReference<E2> forBackReferenceableTableNamesAndBackReferencedFieldName(
    final IContainer<String> backReferenceableTableNames,
    final String backReferencedFieldName) {
    return new MultiBackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  public static <E2 extends IEntity> MultiBackReference<E2> forBackReferenceableTablesAndBackReferencedFieldName(
    final IContainer<ITable<IEntity>> backReferenceableTables,
    final String backReferencedFieldName) {
    final var backReferenceableTableNames = backReferenceableTables.getViewOf(ITable::getName);

    return new MultiBackReference<>(backReferenceableTableNames, backReferencedFieldName);
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
  public IContainer<IBaseReference> getStoredBaseReferencesWhoAreBackReferencedFromThis() {
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
    setAsEditedAndRunPotentialUpdateAction();
  }

  void internalDeleteBackReferencedEntityId(final String backReferencedEntityId) {
    final var entry = localEntries.getStoredFirst(e -> e.getBackReferencedEntityId().equals(backReferencedEntityId));

    entry.setDeleted();
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

    //TODO: Create MultiBackReferenceEntryMapper
    return //
    multiBackReferenceEntries.to(e -> //
    MultiBackReferenceEntry
      .createLoadedEntryForMultiBackReferenceAndBackReferencedEntityIdAndBackReferencedTableId(
        this,
        e.backReferencedEntityId(),
        e.backReferencedEntityTableId()));
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
