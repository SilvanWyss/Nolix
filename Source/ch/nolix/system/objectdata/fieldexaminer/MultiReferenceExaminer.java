package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiReferenceExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

/**
 * @author Silvan Wyss
 */
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
    && entity.isOpen()
    && multiReference.getReferenceableTableNames().contains(entity.getParentTableName());
  }

  @Override
  public <E extends IEntity> boolean canBeCleared(final IMultiReference<E> multiReference) {
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
