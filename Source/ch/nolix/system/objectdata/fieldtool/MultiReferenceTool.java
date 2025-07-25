package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.fieldtool.IMultiReferenceTool;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public final class MultiReferenceTool extends FieldTool implements IMultiReferenceTool {

  @Override
  public <E extends IEntity> boolean canAddEntity(final IMultiReference<E> multiReference) {
    return //
    multiReference != null
    && multiReference.isOpen();
  }

  @Override
  public <E extends IEntity> boolean canAddEntity(final IMultiReference<E> multiReference, IEntity entity) {
    return //
    canAddEntity(multiReference)
    && entity != null
    && entity.isOpen()
    && multiReference.getReferencedTableName().equals(entity.getParentTableName());
  }

  @Override
  public <E extends IEntity> boolean canClear(final IMultiReference<E> multiReference) {
    return //
    multiReference != null
    && multiReference.isOpen();
  }

  @Override
  public <E extends IEntity> boolean canRemoveEntity(final IMultiReference<E> multiReference, final E entity) {
    return //
    canRemoveEntity(multiReference)
    && entity != null
    && entity.isOpen();
  }

  private <E extends IEntity> boolean canRemoveEntity(final IMultiReference<E> multiReference) {
    return //
    multiReference != null
    && multiReference.isOpen();
  }
}
