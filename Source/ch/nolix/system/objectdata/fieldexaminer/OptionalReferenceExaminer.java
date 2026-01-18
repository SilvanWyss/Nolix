package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IOptionalReferenceExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

/**
 * @author Silvan Wyss
 */
public final class OptionalReferenceExaminer extends FieldExaminer implements IOptionalReferenceExaminer {
  @Override
  public <E extends IEntity> boolean canBeCleared(final IOptionalReference<E> optionalReference) {
    return //
    optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> boolean canSetEntity(final IOptionalReference<E> optionalReference) {
    return //
    optionalReference != null
    && optionalReference.isOpen()
    && optionalReference.belongsToEntity();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> boolean canSetEntity(final IOptionalReference<E> optionalReference, final E entity) {
    return //
    canSetEntity(optionalReference)
    && entity != null
    && entity.isOpen()
    && optionalReference.getReferenceableTableNames().contains(entity.getParentTableName());
  }
}
