package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IReferenceExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

public final class ReferenceExaminer extends FieldExaminer implements IReferenceExaminer {
  @Override
  public <E extends IEntity> boolean canSetEntity(final IReference<E> reference) {
    return //
    reference != null
    && reference.isOpen()
    && reference.belongsToEntity();
  }

  @Override
  public <E extends IEntity> boolean canSetEntity(final IReference<E> reference, final E entity) {
    return //
    canSetEntity(reference)
    && entity != null
    && entity.isOpen()
    && reference.getReferenceableTableNames().contains(entity.getParentTableName());
  }
}
