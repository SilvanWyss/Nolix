package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public interface IOptionalReferenceExaminer extends IFieldExaminer {

  <E extends IEntity> boolean canClear(IOptionalReference<E> optionalReference);

  <E extends IEntity> boolean canSetEntity(IOptionalReference<E> optionalReference);

  <E extends IEntity> boolean canSetEntity(IOptionalReference<E> optionalReference, E entity);
}
