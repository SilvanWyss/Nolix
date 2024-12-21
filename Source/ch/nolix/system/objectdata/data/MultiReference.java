package ch.nolix.system.objectdata.data;

import java.util.function.Predicate;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.fieldtool.MultiReferenceEntryTool;
import ch.nolix.system.objectdata.fieldtool.MultiReferenceTool;
import ch.nolix.system.objectdata.fieldvalidator.MultiReferenceValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldWithContentAsStringDto;

public final class MultiReference<E extends IEntity> extends BaseReference<E> implements IMultiReference<E> {

  private static final BaseReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseReferenceUpdater();

  private static final MultiReferenceTool MULTI_REFERENCE_TOOL = new MultiReferenceTool();

  private static final MultiReferenceValidator MULTI_REFERENCE_VALIDATOR = new MultiReferenceValidator();

  private static final MultiReferenceEntryTool MULTI_REFERENCE_ENTRY_TOOL = new MultiReferenceEntryTool();

  private boolean loadedAllPersistedReferencedEntityIds;

  private final LinkedList<MultiReferenceEntry<E>> localEntries = LinkedList.createEmpty();

  private MultiReference(final String referencedTableName) {
    super(referencedTableName);
  }

  public static <E2 extends Entity> MultiReference<E2> forReferencedEntityType(final Class<E2> referencedEntityType) {

    final var referencedTableName = referencedEntityType.getSimpleName();

    return forReferencedTable(referencedTableName);
  }

  public static <E2 extends Entity> MultiReference<E2> forReferencedTable(final String referencedTableName) {
    return new MultiReference<>(referencedTableName);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void addEntity(final Object entity) {
    addCastedEntity((E) entity);
  }

  @Override
  public void clear() {

    getAllStoredReferencedEntities().forEach(this::removeEntity);

    setAsEditedAndRunPotentialUpdateAction();
  }

  @Override
  public IContainer<String> getAllReferencedEntityIds() {

    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(MULTI_REFERENCE_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiReferenceEntry::getReferencedEntityId);
  }

  @Override
  public IContainer<E> getAllStoredReferencedEntities() {

    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(MULTI_REFERENCE_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiReferenceEntry::getStoredReferencedEntity);
  }

  @Override
  @SuppressWarnings("unchecked")
  public IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences() {

    final ILinkedList<IBaseBackReference<IEntity>> backReferencingFields = LinkedList.createEmpty();

    for (final var re : getAllStoredReferencedEntities()) {

      final var backReferencingField = re.internalGetStoredFields()
        .getOptionalStoredFirst(p -> p.referencesBackField(this));

      if (backReferencingField.isPresent()) {
        backReferencingFields.addAtEnd((IBaseBackReference<IEntity>) backReferencingField.get());
      }
    }

    return backReferencingFields;
  }

  @Override
  public IContainer<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(MULTI_REFERENCE_ENTRY_TOOL::isNewOrDeleted);
  }

  @Override
  public ContentType getType() {
    return ContentType.MULTI_REFERENCE;
  }

  @Override
  public void internalSetOrClearContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
  }

  @Override
  public ContentFieldWithContentAsStringDto internalToContentField() {
    return ContentFieldWithContentAsStringDto.withColumnName(getName());
  }

  @Override
  public boolean isEmpty() {
    return getAllReferencedEntityIds().isEmpty();
  }

  @Override
  public boolean isMandatory() {
    return false;
  }

  @Override
  public boolean loadedAllPersistedReferencedEntityIds() {
    return loadedAllPersistedReferencedEntityIds;
  }

  @Override
  public boolean referencesEntity(final IEntity entity) {

    if (entity == null) {
      return false;
    }

    return getAllReferencedEntityIds().containsEqualing(entity.getId());
  }

  @Override
  public boolean referencesUninsertedEntity() {
    return getAllStoredReferencedEntities().containsAny(e -> !e.belongsToTable());
  }

  @Override
  @SuppressWarnings("unchecked")
  public void removeEntity(final Object entity) {
    removeCastedEntity((E) entity);
  }

  @Override
  public void removeFirstEntity(final Predicate<E> selector) {

    final var entity = getAllStoredReferencedEntities().getOptionalStoredFirst(selector);

    entity.ifPresent(this::removeEntity);
  }

  @Override
  void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    if (containsAny()) {
      for (final var e : getAllStoredReferencedEntities()) {
        updateProbableBackReferenceForSetOrAddedEntity(e);
      }
    }
  }

  private void addCastedEntity(final E entity) {

    assertCanAddEntity(entity);

    updateStateAddingEntity(entity);

    updatePotentialBaseBackReferenceOfEntityForAddEntity(entity);

    insertEntityIntoDatabaseIfPossible(entity);

    setAsEditedAndRunPotentialUpdateAction();
  }

  private void assertCanAddEntity(final E entity) {
    MULTI_REFERENCE_VALIDATOR.assertCanAddGivenEntity(this, entity);
  }

  private void insertEntityIntoDatabaseIfPossible(final E entity) {
    if (belongsToEntity()
    && getStoredParentEntity().belongsToTable()
    && entity.getState() == DatabaseObjectState.NEW
    && !entity.belongsToTable()) {
      getStoredParentEntity().getStoredParentDatabase().insertEntity(entity);
    }
  }

  private IContainer<MultiReferenceEntry<E>> loadAllPersistedReferencedEntityIds() {
    return internalGetStoredDataAndSchemaAdapter().loadMultiReferenceEntries(
      getStoredParentEntity().getParentTableName(),
      getStoredParentEntity().getId(),
      getName())
      .to(rei -> MultiReferenceEntry.loadedEntryForMultiReferenceAndReferencedEntityId(this, rei));
  }

  private boolean needsToLoadAllPersistedReferencedEntityIds() {
    return !loadedAllPersistedReferencedEntityIds()
    && MULTI_REFERENCE_TOOL.belongsToLoadedEntity(this);
  }

  private void removeCastedEntity(final E entity) {

    MULTI_REFERENCE_VALIDATOR.assertCanRemoveEntity(this, entity);

    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    localEntries.getStoredFirst(le -> le.getReferencedEntityId().equals(entity.getId())).internalSetDeleted();

    setAsEditedAndRunPotentialUpdateAction();
  }

  private void updatePotentialBaseBackReferenceOfEntityForAddEntity(final E entity) {
    BASE_BACK_REFERENCE_UPDATER.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  private void updateStateAddingEntity(final E entity) {
    localEntries.addAtEnd(MultiReferenceEntry.newEntryForMultiReferenceAndReferencedEntityId(this, entity.getId()));
  }

  private void updateStateLoadingAllPersistedReferencedEntityIds() {

    loadedAllPersistedReferencedEntityIds = true;

    localEntries.addAtEnd(loadAllPersistedReferencedEntityIds());
  }

  private void updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded() {
    if (needsToLoadAllPersistedReferencedEntityIds()) {
      updateStateLoadingAllPersistedReferencedEntityIds();
    }
  }
}
