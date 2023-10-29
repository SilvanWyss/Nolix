//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class MultiReferenceHelper extends PropertyHelper implements IMultiReferenceHelper {

  @Override
  public boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity) {
    return canAddEntity(multiReference)
    && entity != null
    && entity.isOpen()
    && multiReference.getReferencedTableName().equals(entity.getParentTableName());
  }

  //method
  @Override
  public boolean canClear(IMultiReference<?> multiReference) {
    return multiReference != null
    && multiReference.belongsToEntity()
    && multiReference.getStoredParentEntity().isOpen();
  }

  //method
  @Override
  public <E extends IEntity> boolean canRemoveEntity(
    final IMultiReference<E> multiReference,
    final E entity) {
    return canRemoveEntity(multiReference)
    && entity.isOpen();
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForAddEntity(
    final IMultiReference<?> multiReference,
    final IEntity entity) {

    final var parentEntity = multiReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(multiReference.getName()));
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForClear(final IMultiReference<?> multiReference) {

    final var parentEntity = multiReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(multiReference.getName()));
  }

  //method
  private boolean canAddEntity(final IMultiReference<?> multiReference) {
    return multiReference != null
    && multiReference.belongsToEntity()
    && multiReference.getStoredParentEntity().isOpen();
  }

  //method
  private boolean canRemoveEntity(IMultiReference<?> multiReference) {
    return multiReference != null
    && multiReference.isOpen();
  }
}
