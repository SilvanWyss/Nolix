package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IMultiReferenceTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;

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
