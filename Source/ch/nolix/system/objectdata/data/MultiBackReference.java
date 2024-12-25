package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReferenceEntry;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class MultiBackReference<E extends IEntity> extends AbstractBackReference<E>
implements IMultiBackReference<E> {

  private static final DatabaseObjectExaminer DATABASE_OBJECT_TOOL = new DatabaseObjectExaminer();

  private static final FieldTool FIELD_TOOL = new FieldTool();

  private boolean loadedAllPersistedBackReferencedEntityIds;

  private final LinkedList<MultiBackReferenceEntry<E>> localEntries = LinkedList.createEmpty();

  private MultiBackReference(final String backReferencedTableName, final String backReferencedBaseReferenceName) {
    super(backReferencedTableName, backReferencedBaseReferenceName);
  }

  public static <E2 extends Entity> MultiBackReference<E2> forBackReferencedEntityTypeAndBaseReference(
    final Class<E2> backReferencedEntityType,
    final String backReferencedBaseReferenceName) {

    final var entityTypeName = backReferencedEntityType.getSimpleName();

    return forBackReferencedTableAndBaseReference(entityTypeName, backReferencedBaseReferenceName);
  }

  public static <E2 extends Entity> MultiBackReference<E2> forBackReferencedTableAndBaseReference(
    final String backReferencedTableName,
    final String backReferencedBaseReference) {
    return new MultiBackReference<>(backReferencedTableName, backReferencedBaseReference);
  }

  @Override
  public IContainer<String> getAllBackReferencedEntityIds() {

    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getBackReferencedEntityId);
  }

  @Override
  public IContainer<E> getAllStoredBackReferencedEntities() {

    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getStoredBackReferencedEntity);
  }

  @Override
  public IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrDeleted);
  }

  @Override
  public IContainer<IField> getStoredReferencingFields() {

    final ILinkedList<IField> referencingFields = LinkedList.createEmpty();
    final var backReferencedBaseReferenceName = getBackReferencedFieldName();

    for (final var e : getAllStoredBackReferencedEntities()) {
      for (final var f : e.internalGetStoredFields()) {
        if (f.hasName(backReferencedBaseReferenceName)) {
          referencingFields.addAtEnd(f);
          break;
        }
      }
    }

    return referencingFields;
  }

  @Override
  public ContentType getType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }

  @Override
  public void internalSetOrClearContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
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
  protected boolean referencesBackEntityWithId(final String id) {

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

    return internalGetStoredDataAndSchemaAdapter().loadMultiBackReferenceEntries(
      entity.getParentTableName(),
      entity.getId(),
      getName())
      .to(e -> MultiBackReferenceEntry.loadedEntryForMultiBackReferenceAndReferencedEntityId(this, e));
  }

  private boolean needsToLoadAllPersistedBackReferencedEntityIds() {
    return !loadedAllPersistedReferencedEntityIds()
    && FIELD_TOOL.belongsToLoadedEntity(this);
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
