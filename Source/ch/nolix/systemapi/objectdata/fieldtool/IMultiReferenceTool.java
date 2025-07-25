package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public interface IMultiReferenceTool extends IFieldTool {

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);

  boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  boolean canClear(IMultiReference<?> multiReference);
}
