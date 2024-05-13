//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.fieldtool.MultiReferenceEntryTool;
import ch.nolix.system.objectdata.fieldtool.MultiReferenceTool;
import ch.nolix.system.objectdata.fieldvalidator.MultiReferenceValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class MultiReference<E extends IEntity> extends BaseReference<E> implements IMultiReference<E> {

  //constant
  private static final BaseReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseReferenceUpdater();

  //constant
  private static final MultiReferenceTool MULTI_REFERENCE_TOOL = new MultiReferenceTool();

  //constant
  private static final MultiReferenceValidator MULTI_REFERENCE_VALIDATOR = new MultiReferenceValidator();

  //constant
  private static final MultiReferenceEntryTool MULTI_REFERENCE_ENTRY_TOOL = new MultiReferenceEntryTool();

  //attribute
  private boolean loadedAllPersistedReferencedEntityIds;

  //multi-attribute
  private final LinkedList<MultiReferenceEntry<E>> localEntries = new LinkedList<>();

  //constructor
  private MultiReference(final String referencedTableName) {
    super(referencedTableName);
  }

  //static method
  public static <E2 extends Entity> MultiReference<E2> forReferencedEntityType(final Class<E2> referencedEntityType) {

    final var referencedTableName = referencedEntityType.getSimpleName();

    return forReferencedTable(referencedTableName);
  }

  //static method
  public static <E2 extends Entity> MultiReference<E2> forReferencedTable(final String referencedTableName) {
    return new MultiReference<>(referencedTableName);
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  public void addEntity(final Object entity) {
    addCastedEntity((E) entity);
  }

  //method
  @Override
  public void clear() {

    getAllStoredReferencedEntities().forEach(this::removeEntity);

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  @Override
  public IContainer<String> getAllReferencedEntityIds() {

    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(MULTI_REFERENCE_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiReferenceEntry::getReferencedEntityId);
  }

  //method
  @Override
  public IContainer<E> getAllStoredReferencedEntities() {

    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(MULTI_REFERENCE_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiReferenceEntry::getStoredReferencedEntity);
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  public IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences() {

    final var backReferencingFields = new LinkedList<IBaseBackReference<IEntity>>();

    for (final var re : getAllStoredReferencedEntities()) {

      final var backReferencingField = re.internalGetStoredFields()
        .getOptionalStoredFirst(p -> p.referencesBackField(this));

      if (backReferencingField.isPresent()) {
        backReferencingFields.addAtEnd((IBaseBackReference<IEntity>) backReferencingField.get());
      }
    }

    return backReferencingFields;
  }

  //method
  @Override
  public IContainer<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(MULTI_REFERENCE_ENTRY_TOOL::isNewOrDeleted);
  }

  //method
  @Override
  public ContentType getType() {
    return ContentType.MULTI_REFERENCE;
  }

  //method
  @Override
  public IContentFieldDto internalToContentField() {
    return new ContentFieldDto(getName());
  }

  //method
  @Override
  public boolean isEmpty() {
    return getAllReferencedEntityIds().isEmpty();
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public boolean loadedAllPersistedReferencedEntityIds() {
    return loadedAllPersistedReferencedEntityIds;
  }

  //method
  @Override
  public boolean referencesEntity(final IEntity entity) {

    if (entity == null) {
      return false;
    }

    return getAllReferencedEntityIds().containsEqualing(entity.getId());
  }

  //method
  @Override
  public boolean referencesUninsertedEntity() {
    return getAllStoredReferencedEntities().containsAny(e -> !e.belongsToTable());
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  public void removeEntity(final Object entity) {
    removeCastedEntity((E) entity);
  }

  //method
  @Override
  public void removeFirstEntity(final Predicate<E> selector) {

    final var entity = getAllStoredReferencedEntities().getOptionalStoredFirst(selector);

    entity.ifPresent(this::removeEntity);
  }

  //method
  @Override
  void internalSetOrClearFromContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
  }

  //method
  @Override
  void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    if (containsAny()) {
      for (final var e : getAllStoredReferencedEntities()) {
        updateProbableBackReferenceForSetOrAddedEntity(e);
      }
    }
  }

  //method
  private void addCastedEntity(final E entity) {

    assertCanAddEntity(entity);

    updateStateAddingEntity(entity);

    updatePotentialBaseBackReferenceOfEntityForAddEntity(entity);

    insertEntityIntoDatabaseIfPossible(entity);

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  private void assertCanAddEntity(final E entity) {
    MULTI_REFERENCE_VALIDATOR.assertCanAddGivenEntity(this, entity);
  }

  //method
  private void insertEntityIntoDatabaseIfPossible(final E entity) {
    if (belongsToEntity()
    && getStoredParentEntity().belongsToTable()
    && entity.getState() == DatabaseObjectState.NEW
    && !entity.belongsToTable()) {
      getStoredParentEntity().getStoredParentDatabase().insertEntity(entity);
    }
  }

  //method
  private IContainer<MultiReferenceEntry<E>> loadAllPersistedReferencedEntityIds() {
    return internalGetStoredDataAndSchemaAdapter().loadMultiReferenceEntries(
      getStoredParentEntity().getParentTableName(),
      getStoredParentEntity().getId(),
      getName())
      .to(rei -> MultiReferenceEntry.loadedEntryForMultiReferenceAndReferencedEntityId(this, rei));
  }

  //method
  private boolean needsToLoadAllPersistedReferencedEntityIds() {
    return !loadedAllPersistedReferencedEntityIds()
    && MULTI_REFERENCE_TOOL.belongsToLoadedEntity(this);
  }

  //method
  private void removeCastedEntity(final E entity) {

    MULTI_REFERENCE_VALIDATOR.assertCanRemoveEntity(this, entity);

    updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded();

    localEntries.getStoredFirst(le -> le.getReferencedEntityId().equals(entity.getId())).internalSetDeleted();

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  private void updatePotentialBaseBackReferenceOfEntityForAddEntity(final E entity) {
    BASE_BACK_REFERENCE_UPDATER.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  //method
  private void updateStateAddingEntity(final E entity) {
    localEntries.addAtEnd(MultiReferenceEntry.newEntryForMultiReferenceAndReferencedEntityId(this, entity.getId()));
  }

  //method
  private void updateStateLoadingAllPersistedReferencedEntityIds() {

    loadedAllPersistedReferencedEntityIds = true;

    localEntries.addAtEnd(loadAllPersistedReferencedEntityIds());
  }

  //method
  private void updateStateLoadingAllPersistedReferencedEntityIdsIfNotLoaded() {
    if (needsToLoadAllPersistedReferencedEntityIds()) {
      updateStateLoadingAllPersistedReferencedEntityIds();
    }
  }
}
