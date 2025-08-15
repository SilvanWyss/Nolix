package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiReferenceExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public final class MultiReferenceExaminer extends FieldExaminer implements IMultiReferenceExaminer {

  @Override
  public <E extends IEntity> boolean canAddEntity(final IMultiReference<E> multiReference) {
    return //
    multiReference != null
    && multiReference.isOpen();
  }

  @Override
  public <E extends IEntity> boolean canAddEntity(final IMultiReference<E> multiReference, final E entity) {
    return //
    canAddEntity(multiReference)
    && entity != null
    && entity.isOpen();
    //TODO: && entity.getClass().isAssignableFrom(multiReference.getReferencedEntityType())
  }

  @Override
  public <E extends IEntity> boolean canClear(final IMultiReference<E> multiReference) {
    return //
    multiReference != null
    && multiReference.isOpen();
  }

  @Override
  public <E extends IEntity> boolean canRemoveEntity(final IMultiReference<E> multiReference) {
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
}
