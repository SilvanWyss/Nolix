package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

public interface IReferenceExaminer extends IFieldExaminer {

  <E extends IEntity> boolean canSetEntity(IReference<E> reference);

  <E extends IEntity> boolean canSetEntity(final IReference<E> reference, IEntity entity);
}
