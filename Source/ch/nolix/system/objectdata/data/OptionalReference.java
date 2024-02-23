//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.propertytool.OptionalReferenceTool;
import ch.nolix.system.objectdata.propertyvalidator.OptionalReferenceValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IOptionalReferenceValidator;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class OptionalReference<E extends IEntity> extends BaseReference<E> implements IOptionalReference<E> {

  //constant
  private static final BaseReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseReferenceUpdater();

  //constant
  private static final IOptionalReferenceValidator OPTIONAL_REFERENCE_VALIDATOR = new OptionalReferenceValidator();

  //constant
  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  //constant
  private static final IOptionalReferenceTool OPTIONAL_REFERENCE_TOOL = new OptionalReferenceTool();

  //optional attribute
  private String referencedEntityId;

  //constructor
  private OptionalReference(final String referencedTableName) {
    super(referencedTableName);
  }

  //static method
  public static <E2 extends Entity> OptionalReference<E2> forEntity(final Class<E2> type) {
    return new OptionalReference<>(type.getSimpleName());
  }

  //static method
  public static OptionalReference<BaseEntity> forEntityWithTableName(final String tableName) {
    return new OptionalReference<>(tableName);
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
  public E getReferencedEntity() {
    return getReferencedTable().getStoredEntityById(getReferencedEntityId());
  }

  //method
  @Override
  public String getReferencedEntityId() {

    OPTIONAL_REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    return referencedEntityId;
  }

  //method
  @Override
  public IContainer<IProperty> getStoredBackReferencingProperties() {

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    final var backReferencingProperty = getReferencedEntity().internalGetStoredProperties()
      .getOptionalStoredFirst(p -> p.referencesBackProperty(this));

    if (backReferencingProperty.isPresent()) {
      return ImmutableList.withElement(backReferencingProperty.get());
    }

    return new ImmutableList<>();
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.OPTIONAL_REFERENCE;
  }

  //method
  @Override
  public IContentFieldDto internalToContentField() {

    if (isEmpty()) {
      return new ContentFieldDto(getName());
    }

    return new ContentFieldDto(getName(), getReferencedEntityId());
  }

  //method
  @Override
  public boolean isEmpty() {
    return (referencedEntityId == null);
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public boolean referencesEntity(IEntity entity) {
    return containsAny()
    && entity != null
    && getReferencedEntityId().equals(entity.getId());
  }

  //method
  @Override
  public boolean referencesUninsertedEntity() {
    return containsAny()
    && !getReferencedEntity().belongsToTable();
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  public void setEntity(final Object entity) {
    setCastedEntity((E) entity);
  }

  //method
  @Override
  public void setEntityById(final String id) {

    final var entity = getReferencedTable().getStoredEntityById(id);

    setEntity(entity);
  }

  //method
  @Override
  void internalSetOrClearFromContent(final Object content) {
    if (content == null) {
      referencedEntityId = null;
    } else {
      referencedEntityId = (String) content;
    }
  }

  //method
  @Override
  void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    if (containsAny()) {
      updateProbableBackReferenceForSetOrAddedEntity(getReferencedEntity());
    }
  }

  //method
  private void assertCanClear() {
    OPTIONAL_REFERENCE_VALIDATOR.assertCanClear(this);
  }

  //method
  private void assertCanSetEntity(final E entity) {
    OPTIONAL_REFERENCE_VALIDATOR.assertCanSetGivenEntity(this, entity);
  }

  //method
  private void clearWhenContainsAny() {

    assertCanClear();

    updateProbableBackReferencingPropertyForClear();

    updateStateForClear();

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  private Optional<? extends IProperty> getOptionalPendantReferencingPropertyToEntity(final E entity) {
    return ENTITY_TOOL
      .getStoredReferencingProperties(entity)
      .getOptionalStoredFirst(rp -> rp.hasName(getName()));
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
  private void setCastedEntity(final E entity) {

    assertCanSetEntity(entity);

    updatePropbableBackReferencingPropertyOfEntityForClear(entity);

    clear();

    updateStateForSetEntity(entity);

    updatePotentialBaseBackReferenceOfEntityForSetEntity(entity);

    insertEntityIntoDatabaseIfPossible(entity);

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  private void updateBackReferencingPropertyForClear(final IProperty backReferencingProperty) {
    switch (backReferencingProperty.getType()) {
      case BACK_REFERENCE:
        final var backReference = (BackReference<?>) backReferencingProperty;
        backReference.internalClear();
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) backReferencingProperty;
        optionalBackReference.internalClear();
        break;
      case MULTI_BACK_REFERENCE:
        /*
         * Does nothing. MultiBackReferences do not need to be updated, because
         * MultiBackReferences do not have redundancies.
         */
        break;
      default:
        //Does nothing.
    }
  }

  //method
  private void updatePotentialBaseBackReferenceOfEntityForSetEntity(final E entity) {
    BASE_BACK_REFERENCE_UPDATER.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  //method
  private void updateProbableBackReferencingPropertyForClear() {
    if (containsAny()) {
      updateProbableBackReferencingPropertyForClearWhenIsNotEmpty();
    }
  }

  //method
  private void updateProbableBackReferencingPropertyForClearWhenIsNotEmpty() {

    final var backReferencingProperty = OPTIONAL_REFERENCE_TOOL.getOptionalStoredBackReferencingProperty(this);

    backReferencingProperty.ifPresent(this::updateBackReferencingPropertyForClear);
  }

  //method
  private void updatePropbableBackReferencingPropertyOfEntityForClear(final E entity) {

    final var pendantReferencingProperty = getOptionalPendantReferencingPropertyToEntity(entity);

    if (pendantReferencingProperty.isPresent()) {
      final var reference = (OptionalReference<?>) pendantReferencingProperty.get();
      reference.clear();
    }
  }

  //method
  private void updateStateForSetEntity(final E entity) {
    referencedEntityId = entity.getId();
  }

  //method
  private void updateStateForClear() {
    referencedEntityId = null;
  }
}
