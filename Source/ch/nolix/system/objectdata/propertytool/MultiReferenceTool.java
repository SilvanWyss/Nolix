//package declaration
package ch.nolix.system.objectdata.propertytool;

//own imports
import ch.nolix.system.sqlrawdatabase.databasedto.ContentFieldDto;
import ch.nolix.system.sqlrawdatabase.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IMultiReferenceTool;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class MultiReferenceTool extends PropertyTool implements IMultiReferenceTool {

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
