//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.propertyhelper.OptionalReferenceHelper;
import ch.nolix.system.objectdatabase.propertyvalidator.OptionalReferenceValidator;
import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IOptionalReferenceValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class OptionalReference<E extends IEntity> extends BaseReference<E> implements IOptionalReference<E> {

  //constant
  private static final IOptionalReferenceValidator OPTIONAL_REFERENCE_VALIDATOR = new OptionalReferenceValidator();

  //constant
  private static final IEntityHelper ENTITY_HELPER = new EntityHelper();

  //constant
  private static final IOptionalReferenceHelper OPTIONAL_REFERENCE_HELPER = new OptionalReferenceHelper();

  //static method
  public static <E2 extends Entity> OptionalReference<E2> forEntity(final Class<E2> type) {
    return new OptionalReference<>(type.getSimpleName());
  }

  //static method
  public static OptionalReference<BaseEntity> forEntityWithTableName(final String tableName) {
    return new OptionalReference<>(tableName);
  }

  //optional attribute
  private String referencedEntityId;

  //constructor
  private OptionalReference(final String referencedTableName) {
    super(referencedTableName);
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

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    final var backReferencingProperty = getReferencedEntity().technicalGetRefProperties()
        .getStoredFirstOrNull(p -> p.referencesBackProperty(this));

    if (backReferencingProperty != null) {
      return ImmutableList.withElement(backReferencingProperty);
    }

    return new ImmutableList<>();
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
  public PropertyType getType() {
    return PropertyType.OPTIONAL_REFERENCE;
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
  public void setEntity(final E entity) {

    assertCanSetEntity(entity);

    updatePropbableBackReferencingPropertyOfEntityForClear(entity);

    clear();

    updateStateForSetEntity(entity);

    updateProbableBackReferencingPropertyForSetOrAddedEntity(entity);

    setAsEditedAndRunProbableUpdateAction();
  }

  //method
  @Override
  public void setEntityById(final String id) {

    final var entity = getReferencedTable().getStoredEntityById(id);

    setEntity(entity);
  }

  //method
  @Override
  public IContentFieldDto technicalToContentField() {

    if (isEmpty()) {
      return new ContentFieldDto(getName());
    }

    return new ContentFieldDto(getName(), getReferencedEntityId());
  }

  //method
  @Override
  void internalSetOrClearDirectlyFromContent(final Object content) {
    if (content == null) {
      referencedEntityId = null;
    } else {
      referencedEntityId = (String) content;
    }
  }

  //method
  @Override
  void internalUpdateProbableBackReferencesWhenIsNew() {
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

    setAsEditedAndRunProbableUpdateAction();
  }

  //method
  private IProperty getPendantReferencingPropertyToEntityOrNull(final E entity) {
    return ENTITY_HELPER.getStoredReferencingProperties(entity).getStoredFirstOrNull(rp -> rp.hasName(getName()));
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
  private void updateProbableBackReferencingPropertyForClear() {
    if (containsAny()) {
      updateProbableBackReferencingPropertyForClearWhenIsNotEmpty();
    }
  }

  //method
  private void updateProbableBackReferencingPropertyForClearWhenIsNotEmpty() {

    final var backReferencingProperty = OPTIONAL_REFERENCE_HELPER.getStoredBackReferencingPropertyOrNull(this);

    if (backReferencingProperty != null) {
      updateBackReferencingPropertyForClear(backReferencingProperty);
    }
  }

  //method
  private void updateProbableBackReferencingPropertyForSetOrAddedEntity(final E entity) {
    for (final var p : entity.technicalGetRefProperties()) {
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
  private void updatePropbableBackReferencingPropertyOfEntityForClear(final E entity) {

    final var pendantReferencingProperty = getPendantReferencingPropertyToEntityOrNull(entity);

    if (pendantReferencingProperty != null) {
      final var reference = (OptionalReference<?>) pendantReferencingProperty;
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
