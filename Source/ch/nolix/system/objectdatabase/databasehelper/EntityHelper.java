//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityHeadDto;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDto;
import ch.nolix.system.sqldatabaserawdata.databasedto.NewEntityDto;
import ch.nolix.systemapi.entitypropertyapi.mainapi.BasePropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;

//class
public final class EntityHelper extends DatabaseObjectHelper implements IEntityHelper {

  //constant
  private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();

  //method
  @Override
  public boolean allNewAndEditedMandatoryPropertiesAreSet(final IEntity entity) {

    if (isNewOrEdited(entity)) {
      return entity.technicalGetRefProperties().containsOnly(PROPERTY_HELPER::isSetForCaseIsNewOrEditedAndMandatory);
    }

    return true;
  }

  //method
  @Override
  public boolean canBeDeleted(final IEntity entity) {
    return (isLoaded(entity) && !isReferenced(entity));
  }

  //method
  @Override
  public boolean canBeInsertedIntoTable(final IEntity entity) {
    return isNew(entity)
    && entity.belongsToTable();
  }

  //method
  @Override
  public boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(final IEntity entity) {
    return entity.technicalGetRefProperties().containsAny(this::isMandatoryAndEmptyBaseValueOrBaseReference);
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForEntity(final IEntity entity) {
    return new EntityUpdateDto(
      entity.getId(),
      entity.getSaveStamp(),
      getStoredEditedProperties(entity).to(IProperty::technicalToContentField));
  }

  //method
  @Override
  public IEntityHeadDto createEntityHeadDtoForEntity(IEntity entity) {
    return new EntityHeadDto(entity.getId(), entity.getSaveStamp());
  }

  //method
  @Override
  public INewEntityDto createNewEntityDtoForEntity(final IEntity entity) {
    return new NewEntityDto(entity.getId(), entity.technicalGetRefProperties().to(IProperty::technicalToContentField));
  }

  //method
  @Override
  public IContainer<IProperty> getStoredBackReferencingProperties(final IEntity entity) {
    return entity.technicalGetRefProperties().toFromGroups(IProperty::getStoredBackReferencingProperties);
  }

  //method
  @Override
  public IContainer<? extends IProperty> getStoredEditedProperties(final IEntity entity) {
    return entity.technicalGetRefProperties().getStoredSelected(PROPERTY_HELPER::isEdited);
  }

  //method
  @Override
  public IContainer<? extends IProperty> getStoredReferencingProperties(final IEntity entity) {
    return entity.technicalGetRefProperties().toFromGroups(IProperty::getStoredReferencingProperties);
  }

  //method
  @Override
  public boolean isReferenced(final IEntity entity) {
    return (isReferencedInLocalData(entity) || entity.isReferencedInPersistedData());
  }

  //method
  @Override
  public boolean isReferencedInLocalData(final IEntity entity) {

    if (!entity.belongsToTable()) {
      return false;
    }

    for (final var t : entity.getStoredParentTable().getStoredParentDatabase().getStoredTables()) {
      if (t.technicalGetRefEntitiesInLocalData().containsAny(e -> referencesGivenEntity(e, entity))) {
        return true;
      }
    }

    return false;
  }

  //method
  @Override
  public boolean referencesGivenEntity(final IEntity sourceEntity, final IEntity entity) {
    return sourceEntity.technicalGetRefProperties().containsAny(p -> p.referencesEntity(entity));
  }

  //method
  @Override
  public boolean referencesUninsertedEntity(final IEntity entity) {
    return entity.technicalGetRefProperties().containsAny(IProperty::referencesUninsertedEntity);
  }

  //method
  private boolean isBaseValueOrBaseReference(final IProperty property) {

    final var baseType = property.getType().getBaseType();

    return baseType == BasePropertyType.BASE_VALUE
    || baseType == BasePropertyType.BASE_REFERENCE;
  }

  //method
  private boolean isMandatoryAndEmptyBaseValueOrBaseReference(final IProperty property) {
    return isBaseValueOrBaseReference(property)
    && PROPERTY_HELPER.isMandatoryAndEmptyBoth(property);
  }
}
