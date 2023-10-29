//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class OptionalReferenceHelper extends PropertyHelper implements IOptionalReferenceHelper {

  //method
  @Override
  public boolean canClear(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }

  //method
  @Override
  public boolean canSetGivenEntity(final IOptionalReference<?> optionalReference, final IEntity entity) {
    return canSetEntity(optionalReference)
    && entity != null
    && entity.isOpen()
    && optionalReference.getReferencedTableName().equals(entity.getParentTableName());
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForClear(final IOptionalReference<?> optionalReference) {

    final var parentEntity = optionalReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(optionalReference.getName()));
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForSetEntity(
    final IOptionalReference<?> optionalReference,
    final IEntity entity) {

    final var parentEntity = optionalReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(optionalReference.getName(), entity.getId()));
  }

  //method
  @Override
  public IProperty getStoredBackReferencingPropertyOrNull(
    final IOptionalReference<?> optionalReference) {
    return optionalReference
      .getReferencedEntity()
      .technicalGetRefProperties()
      .getStoredFirstOrNull(p -> p.referencesBackProperty(optionalReference));
  }

  //method
  private boolean canSetEntity(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }
}
