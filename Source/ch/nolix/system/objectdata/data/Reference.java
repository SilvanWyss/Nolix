//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.propertytool.ReferenceValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IReferenceValidator;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class Reference<E extends IEntity> extends BaseReference<E> implements IReference<E> {

  //constant
  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  //constant
  private static final BaseBackReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseBackReferenceUpdater();

  //constant
  private static final IReferenceValidator REFERENCE_VALIDATOR = new ReferenceValidator();

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
  public String getReferencedEntityId() {

    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    return referencedEntityId;
  }

  //method
  @Override
  public IContainer<IProperty> getStoredBackReferencingProperties() {

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    final var backReferencingProperty = getStoredReferencedEntity().internalGetStoredProperties()
      .getOptionalStoredFirst(p -> p.referencesBackProperty(this));

    if (backReferencingProperty.isPresent()) {
      return ImmutableList.withElement(backReferencingProperty.get());
    }

    return new ImmutableList<>();
  }

  //method
  @Override
  public E getStoredReferencedEntity() {
    return getReferencedTable().getStoredEntityById(getReferencedEntityId());
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.REFERENCE;
  }

  //method
  @Override
  public IContentFieldDto internalToContentField() {
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
    && !getStoredReferencedEntity().belongsToTable();
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
    referencedEntityId = (String) content;
  }

  //method
  @Override
  void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    if (containsAny()) {
      updateProbableBackReferenceForSetOrAddedEntity(getStoredReferencedEntity());
    }
  }

  //method
  private void assertCanSetEntity(final E entity) {
    REFERENCE_VALIDATOR.assertCanSetGivenEntity(this, entity);
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

    updatePropableBackReferencingPropertyOfEntityForClear(entity);

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
        backReference.setAsEditedAndRunPotentialUpdateAction();
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) backReferencingProperty;
        optionalBackReference.internalClear();
        optionalBackReference.setAsEditedAndRunPotentialUpdateAction();
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
    for (final var brp : getStoredBackReferencingProperties()) {
      updateBackReferencingPropertyForClear(brp);
    }
  }

  //method
  private void updatePropableBackReferencingPropertyOfEntityForClear(final E entity) {

    final var pendantReferencingProperty = getOptionalPendantReferencingPropertyToEntity(entity);

    if (pendantReferencingProperty.isPresent()) {
      final var reference = (Reference<?>) pendantReferencingProperty.get();
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
