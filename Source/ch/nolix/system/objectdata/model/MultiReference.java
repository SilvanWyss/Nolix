package ch.nolix.system.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.fieldtool.MultiReferenceEntryTool;
import ch.nolix.system.objectdata.fieldtool.MultiReferenceTool;
import ch.nolix.system.objectdata.fieldvalidator.MultiReferenceValidator;
import ch.nolix.system.objectdata.modelexaminer.FieldExaminer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IFieldExaminer;

public final class MultiReference<E extends IEntity> extends AbstractReference<E> implements IMultiReference<E> {

  private static final BaseReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseReferenceUpdater();

  private static final MultiReferenceTool MULTI_REFERENCE_TOOL = new MultiReferenceTool();

  private static final MultiReferenceValidator MULTI_REFERENCE_VALIDATOR = new MultiReferenceValidator();

  private static final MultiReferenceEntryTool MULTI_REFERENCE_ENTRY_TOOL = new MultiReferenceEntryTool();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private boolean loadedAllPersistedReferencedEntityIds;

  private final LinkedList<MultiReferenceEntry<E>> localEntries = LinkedList.createEmpty();

  private MultiReference(final String referencedTableName) {
    super(referencedTableName);
  }

  public static <E2 extends Entity> MultiReference<E2> forEntity(final Class<E2> referencedEntityType) {

    final var referencedTableName = referencedEntityType.getSimpleName();

    return new MultiReference<>(referencedTableName);
  }

  public static <E2 extends Entity> MultiReference<E2> forTable(final String referencedTableName) {
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

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackThis() {

    final ILinkedList<IAbstractBackReference<IEntity>> abstractBackReferences = LinkedList.createEmpty();

    for (final var e : getAllStoredReferencedEntities()) {

      final var fields = e.internalGetStoredFields();

      final var abstractBackReferenceContainer = fields.getOptionalStoredFirst(f -> f.referencesBackField(this));

      if (abstractBackReferenceContainer.isPresent()) {

        final var abstractBackReference = (IAbstractBackReference<IEntity>) abstractBackReferenceContainer.get();

        abstractBackReferences.addAtEnd(abstractBackReference);
      }
    }

    return abstractBackReferences;
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
  public void internalSetOptionalContent(final Object content) {
    Validator.assertThat(content).thatIsNamed(LowerCaseVariableCatalog.CONTENT).isNull();
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
  void updateBackReferencingFieldsWhenIsInsertedIntoDatabase() {
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
    && FIELD_EXAMINER.belongsToLoadedEntity(this);
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
