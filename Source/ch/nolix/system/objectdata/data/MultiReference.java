//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.propertytool.MultiReferenceTool;
import ch.nolix.system.objectdata.propertyvalidator.MultiReferenceValidator;
import ch.nolix.system.sqlrawdatabase.databasedto.ContentFieldDto;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.entitypropertyapi.mainapi.BasePropertyType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IMultiReferenceTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IMultiReferenceValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class MultiReference<E extends IEntity> extends BaseReference<E> implements IMultiReference<E> {

  //constant
  private static final IMultiReferenceTool MULTI_REFERENCE_TOOL = new MultiReferenceTool();

  //constant
  private static final IMultiReferenceValidator MULTI_REFERENCE_VALIDATOR = new MultiReferenceValidator();

  //attribute
  private boolean extractedReferencedEntityIds;

  //multi-attribute
  private final LinkedList<MultiReferenceEntry<E>> localEntries = new LinkedList<>();

  //constructor
  private MultiReference(final String referencedTableName) {
    super(referencedTableName);
  }

  //static method
  public static <E2 extends Entity> MultiReference<E2> forEntity(final Class<E2> type) {
    return new MultiReference<>(type.getSimpleName());
  }

  //static method
  public static MultiReference<BaseEntity> forEntityWithTableName(final String tableName) {
    return new MultiReference<>(tableName);
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  public void addEntity(final Object entity) {

    final var entityOfConcreteType = (E) entity;

    addEntityOfConcreteType(entityOfConcreteType);
  }

  //method
  @Override
  public void clear() {
    if (containsAny()) {
      clearWhenContainsAny();
    }
  }

  //method
  @Override
  public IContainer<IProperty> getStoredBackReferencingProperties() {

    final var backReferencingProperties = new LinkedList<IProperty>();

    for (final var re : getReferencedEntities()) {

      final var backReferencingProperty = re.technicalGetStoredProperties()
        .getOptionalStoredFirst(p -> p.referencesBackProperty(this));

      if (backReferencingProperty.isPresent()) {
        backReferencingProperties.addAtEnd(backReferencingProperty.get());
      }
    }

    return backReferencingProperties;
  }

  //method
  @Override
  public IContainer<E> getReferencedEntities() {
    return getReferencedEntityIds().to(getReferencedTable()::getStoredEntityById);
  }

  //method
  @Override
  public IContainer<String> getReferencedEntityIds() {

    extractReferencedEntityIdsIfNeeded();

    return localEntries
      .getStoredSelected(MULTI_REFERENCE_TOOL::isNewOrLoaded)
      .to(IMultiReferenceEntry::getReferencedEntityId);
  }

  //method
  @Override
  public IContainer<? extends IMultiReferenceEntry<E>> getStoredLocalEntries() {
    return localEntries;
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.MULTI_REFERENCE;
  }

  //method
  @Override
  public boolean isEmpty() {
    return getReferencedEntityIds().isEmpty();
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public boolean referencesEntity(final IEntity entity) {

    if (entity == null) {
      return false;
    }

    return getReferencedEntityIds().containsEqualing(entity.getId());
  }

  //method
  @Override
  public boolean referencesUninsertedEntity() {
    return getReferencedEntities().containsAny(e -> !e.belongsToTable());
  }

  //method
  @Override
  public void removeEntity(final E entity) {

    MULTI_REFERENCE_VALIDATOR.assertCanRemoveEntity(this, entity);

    extractReferencedEntityIdsIfNeeded();

    localEntries.getStoredFirst(le -> le.getReferencedEntityId().equals(entity.getId())).internalSetDeleted();

    setAsEditedAndRunProbableUpdateAction();
  }

  //method
  @Override
  public IContentFieldDto technicalToContentField() {
    return new ContentFieldDto(getName());
  }

  //method
  @Override
  void internalSetOrClearDirectlyFromContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
  }

  //method
  @Override
  void internalUpdateProbableBackReferencesWhenIsNew() {
    if (containsAny()) {
      for (final var e : getReferencedEntities()) {
        updateProbableBackReferenceForSetOrAddedEntity(e);
      }
    }
  }

  //method
  private void addEntityOfConcreteType(final E entity) {

    assertCanAddEntity(entity);

    updateStateForAddEntity(entity);

    updateProbableBackReferencingPropertyForSetOrAddedEntity(entity);

    insertEntityIntoDatabaseIfPossible(entity);

    setAsEditedAndRunProbableUpdateAction();
  }

  //method
  private void assertCanAddEntity(final E entity) {
    MULTI_REFERENCE_VALIDATOR.assertCanAddGivenEntity(this, entity);
  }

  //method
  private void clearWhenContainsAny() {

    getReferencedEntities().forEach(this::removeEntity);

    setAsEditedAndRunProbableUpdateAction();
  }

  //method
  private boolean extractedReferencedEntityIds() {
    return extractedReferencedEntityIds;
  }

  //method
  private void extractReferencedEntityIdsIfNeeded() {
    if (shouldExtractReferencedEntityIds()) {
      extractReferencedEntityIdsWhenNotLoaded();
    }
  }

  //method
  private void extractReferencedEntityIdsWhenNotLoaded() {

    extractedReferencedEntityIds = true;

    localEntries.addAtEnd(loadReferencedEntityIds());
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
  private IContainer<MultiReferenceEntry<E>> loadReferencedEntityIds() {
    return internalGetRefDataAndSchemaAdapter().loadMultiReferenceEntries(
      getStoredParentEntity().getParentTableName(),
      getStoredParentEntity().getId(),
      getName())
      .to(rei -> MultiReferenceEntry.loadedEntryForMultiReferenceAndReferencedEntityId(this, rei));
  }

  //method
  private boolean shouldExtractReferencedEntityIds() {
    return !extractedReferencedEntityIds()
    && MULTI_REFERENCE_TOOL.belongsToLoadedEntity(this);
  }

  //method
  private void updateProbableBackReferencingPropertyForSetOrAddedEntity(final E entity) {
    for (final var p : entity.technicalGetStoredProperties()) {
      if (p.getType().getBaseType() == BasePropertyType.BASE_BACK_REFERENCE) {

        final var baseBackReference = (BaseBackReference<?>) p;

        if (baseBackReference.getBackReferencedTableName().equals(getStoredParentEntity().getParentTableName())
        && baseBackReference.getBackReferencedPropertyName().equals(getName())) {

          switch (baseBackReference.getType()) {
            case BACK_REFERENCE:
              final var backReference = (BackReference<?>) baseBackReference;
              backReference.internalSetDirectlyBackReferencedEntityId(getStoredParentEntity().getId());
              backReference.setAsEditedAndRunProbableUpdateAction();
              break;
            case OPTIONAL_BACK_REFERENCE:
              final var optionalBackReference = (OptionalBackReference<?>) baseBackReference;
              optionalBackReference.internalSetDirectlyBackReferencedEntityId(getStoredParentEntity().getId());
              optionalBackReference.setAsEditedAndRunProbableUpdateAction();
              break;
            case MULTI_BACK_REFERENCE:
              /*
               * Does nothing. MultiBackReferences do not need to be updated, because
               * MultiBackReferences do not have redundancies.
               */
              break;
            default:
              throw InvalidArgumentException.forArgument(baseBackReference.getType());
          }

          break;
        }
      }
    }
  }

  //method
  private void updateStateForAddEntity(final E entity) {
    localEntries.addAtEnd(MultiReferenceEntry.newEntryForMultiReferenceAndReferencedEntityId(this, entity.getId()));
  }
}
