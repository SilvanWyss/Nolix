package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IMultiReferenceTool;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

public final class MultiReferenceTool extends FieldTool implements IMultiReferenceTool {

  @Override
  public boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity) {
    return canAddEntity(multiReference)
    && entity != null
    && entity.isOpen()
    && multiReference.getReferencedTableName().equals(entity.getParentTableName());
  }

  @Override
  public boolean canClear(IMultiReference<?> multiReference) {
    return multiReference != null
    && multiReference.belongsToEntity()
    && multiReference.getStoredParentEntity().isOpen();
  }

  @Override
  public <E extends IEntity> boolean canRemoveEntity(
    final IMultiReference<E> multiReference,
    final E entity) {
    return canRemoveEntity(multiReference)
    && entity.isOpen();
  }

  @Override
  public IEntityUpdateDto createEntityUpdateDtoForAddEntity(
    final IMultiReference<?> multiReference,
    final IEntity entity) {

    final var parentEntity = multiReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      ContentFieldDto.withColumnName(multiReference.getName()));
  }

  @Override
  public IEntityUpdateDto createEntityUpdateDtoForClear(final IMultiReference<?> multiReference) {

    final var parentEntity = multiReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      ContentFieldDto.withColumnName(multiReference.getName()));
  }

  private boolean canAddEntity(final IMultiReference<?> multiReference) {
    return multiReference != null
    && multiReference.belongsToEntity()
    && multiReference.getStoredParentEntity().isOpen();
  }

  private boolean canRemoveEntity(IMultiReference<?> multiReference) {
    return multiReference != null
    && multiReference.isOpen();
  }
}
