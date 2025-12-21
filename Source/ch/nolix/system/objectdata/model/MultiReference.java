package ch.nolix.system.objectdata.model;

import java.util.Iterator;
import java.util.function.Predicate;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.system.objectdata.fieldexaminer.MultiReferenceExaminer;
import ch.nolix.system.objectdata.fieldvalidator.MultiReferenceValidator;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiReferenceExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IMultiReferenceValidator;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s a {@link MultiReference} can
 *            reference.
 */
public final class MultiReference<E extends IEntity> extends AbstractBaseReference<E> implements IMultiReference<E> {
  private static final IDatabaseObjectExaminer DATABASE_OBJECT_EXAMINER = new DatabaseObjectExaminer();

  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IMultiReferenceExaminer MULTI_REFERENCE_TOOL = new MultiReferenceExaminer();

  private static final IMultiReferenceValidator MULTI_REFERENCE_VALIDATOR = new MultiReferenceValidator();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private boolean loadedAllPersistedReferencedEntityIds;

  private final LinkedList<MultiReferenceEntry<E>> localEntries = LinkedList.createEmpty();

  private MultiReference(final IContainer<String> referenceableTableNames) {
    super(referenceableTableNames);
  }

  @SafeVarargs
  public static <T extends IEntity> MultiReference<T> forEntityTypes(
    final Class<? extends T>... entityTypes) {
    final var entityTypesView = ContainerView.forArray(entityTypes);
    final var referenceableTableNamesView = entityTypesView.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new MultiReference<>(referenceableTableNamesView);
  }

  public static <T extends IEntity> MultiReference<T> forEntityTypes(
    final IContainer<Class<? extends T>> entityTypes) {
    final var referenceableTableNamesView = entityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new MultiReference<>(referenceableTableNamesView);
  }

  public static <T extends IEntity> MultiReference<T> forReferenceableTableNames(
    final IContainer<String> referenceableTableNames) {
    return new MultiReference<>(referenceableTableNames);
  }

  public static <T extends IEntity> MultiReference<T> forReferenceableTableNames(
    final String... referenceableTableNames) {
    final var referenceableTableNamesView = ContainerView.forArray(referenceableTableNames);

    return new MultiReference<>(referenceableTableNamesView);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void addEntity(final Object entity) {
    addCastedEntity((E) entity);
  }

  @Override
  public void clear() {
    getAllStoredReferencedEntities().forEach(this::removeEntity);

    setAsEditedAndRunPossibleUpdateAction();
  }

  @Override
  public IContainer<String> getAllReferencedEntityIds() {
    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    return //
    localEntries
      .getViewOfStoredSelected(MULTI_REFERENCE_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiReferenceEntry::getReferencedEntityId);
  }

  @Override
  public IContainer<E> getAllStoredReferencedEntities() {
    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    return //
    localEntries
      .getViewOfStoredSelected(MULTI_REFERENCE_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiReferenceEntry::getStoredReferencedEntity);
  }

  @Override
  public IContainer<IBaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis() {
    final ILinkedList<IBaseBackReference> abstractBackReferences = LinkedList.createEmpty();

    for (final var e : getAllStoredReferencedEntities()) {
      final var fields = e.internalGetStoredFields();

      final var abstractBackReferenceContainer = fields.getOptionalStoredFirst(f -> f.referencesBackField(this));

      if (abstractBackReferenceContainer.isPresent()) {
        final var abstractBackReference = (IBaseBackReference) abstractBackReferenceContainer.get();

        abstractBackReferences.addAtEnd(abstractBackReference);
      }
    }

    return abstractBackReferences;
  }

  @Override
  public IContainer<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_EXAMINER::isNewOrDeleted);
  }

  @Override
  public FieldType getType() {
    return FieldType.MULTI_REFERENCE;
  }

  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {
    //Does nothing.
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
  public Iterator<E> iterator() {
    return getAllStoredReferencedEntities().iterator();
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
  protected void noteInsertIntoDatabase() {
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

    setAsEditedAndRunPossibleUpdateAction();
  }

  private void assertCanAddEntity(final E entity) {
    MULTI_REFERENCE_VALIDATOR.assertCanAddEntity(this, entity);
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
    return //
    getStoredDataAndSchemaAdapter().loadMultiReferenceEntries(
      getStoredParentEntity().getStoredParentTable().getName(),
      getStoredParentEntity().getId(),
      getName())
      .to(e -> //
      MultiReferenceEntry.createLoadedEntryForMultiReferenceAndReferencedEntityIdAndReferencedTableId(
        this,
        e.referencedEntityId(),
        e.referencedEntityTableId()));
  }

  private boolean needsToLoadAllPersistedReferencedEntityIds() {
    return !loadedAllPersistedReferencedEntityIds()
    && FIELD_EXAMINER.belongsToLoadedEntity(this);
  }

  private void removeCastedEntity(final E entity) {
    MULTI_REFERENCE_VALIDATOR.assertCanRemoveEntity(this, entity);

    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    localEntries.getStoredFirst(le -> le.getReferencedEntityId().equals(entity.getId())).setDeleted();

    setAsEditedAndRunPossibleUpdateAction();
  }

  private void updatePotentialBaseBackReferenceOfEntityForAddEntity(final E entity) {
    BaseReferenceUpdater.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  private void updateStateAddingEntity(final E entity) {
    MultiReferenceEntry<E> multiReferenceEntry;

    if (entity.belongsToTable()) {
      multiReferenceEntry = //
      MultiReferenceEntry.createNewEntryForMultiReferenceAndReferencedEntityIdAndReferencedTableId(
        this,
        entity.getId(),
        entity.getStoredParentTable().getId());
    } else {
      multiReferenceEntry = //
      MultiReferenceEntry.createNewEntryForMultiReferenceAndReferencedEntity(this, entity);
    }

    localEntries.addAtEnd(multiReferenceEntry);
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
