//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.databasetool.EntityTool;
import ch.nolix.system.objectdatabase.propertytool.ReferenceValidator;
import ch.nolix.system.sqlrawdatabase.databasedto.ContentFieldDto;
import ch.nolix.systemapi.entitypropertyapi.mainapi.BasePropertyType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.databasetoolapi.IEntityTool;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IReferenceValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class Reference<E extends IEntity> extends BaseReference<E> implements IReference<E> {

  //constant
  private static final IReferenceValidator REFERENCE_VALIDATOR = new ReferenceValidator();

  //constant
  private static final IEntityTool ENTITY_HELPER = new EntityTool();

  //optional attribute
  private String referencedEntityId;

  //constructor
  private Reference(final String referencedTableName) {
    super(referencedTableName);
  }

  //static method
  public static <E2 extends Entity> Reference<E2> forEntity(final Class<? extends E2> type) {
    return new Reference<>(type.getSimpleName());
  }

  //static method
  public static Reference<BaseEntity> forEntityWithTableName(final String tableName) {
    return new Reference<>(tableName);
  }

  //method
  @Override
  public IContainer<IProperty> getStoredBackReferencingProperties() {

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    final var backReferencingProperty = getReferencedEntity().technicalGetStoredProperties()
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

    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    return referencedEntityId;
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.REFERENCE;
  }

  //method
  @Override
  public boolean isEmpty() {
    return (referencedEntityId == null);
  }

  //method
  @Override
  public boolean isMandatory() {
    return true;
  }

  //method
  @Override
  public boolean referencesEntity(final IEntity entity) {
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
    return new ContentFieldDto(getName(), getReferencedEntityId());
  }

  //method
  @Override
  void internalSetOrClearDirectlyFromContent(final Object content) {
    referencedEntityId = (String) content;
  }

  //method
  @Override
  void internalUpdateProbableBackReferencesWhenIsNew() {
    if (containsAny()) {
      updateProbableBackReferenceForSetOrAddedEntity(getReferencedEntity());
    }
  }

  //method
  private void assertCanSetEntity(final E entity) {
    REFERENCE_VALIDATOR.assertCanSetGivenEntity(this, entity);
  }

  //method
  private void updateBackReferencingPropertyForClear(final IProperty backReferencingProperty) {
    switch (backReferencingProperty.getType()) {
      case BACK_REFERENCE:
        final var backReference = (BackReference<?>) backReferencingProperty;
        backReference.internalClear();
        backReference.setAsEditedAndRunProbableUpdateAction();
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) backReferencingProperty;
        optionalBackReference.internalClear();
        optionalBackReference.setAsEditedAndRunProbableUpdateAction();
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
  private void clear() {
    if (containsAny()) {
      clearWhenContainsAny();
    }
  }

  //method
  private void clearWhenContainsAny() {

    updateProbableBackReferencingPropertyForClear();

    updateStateForClear();

    setAsEditedAndRunProbableUpdateAction();
  }

  //method
  private IProperty getPendantReferencingPropertyToEntityOrNull(final E entity) {
    return ENTITY_HELPER.getStoredReferencingProperties(entity).getStoredFirstOrNull(rp -> rp.hasName(getName()));
  }

  //method
  private void updateProbableBackReferencingPropertyForClear() {
    for (final var brp : getStoredBackReferencingProperties()) {
      updateBackReferencingPropertyForClear(brp);
    }
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
  private void updatePropbableBackReferencingPropertyOfEntityForClear(final E entity) {

    final var pendantReferencingProperty = getPendantReferencingPropertyToEntityOrNull(entity);

    if (pendantReferencingProperty != null) {
      final var reference = (Reference<?>) pendantReferencingProperty;
      reference.clear();
    }
  }

  //method
  private void updateStateForClear() {
    referencedEntityId = null;
  }

  //method
  private void updateStateForSetEntity(final E entity) {
    referencedEntityId = entity.getId();
  }
}
