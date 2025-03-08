package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;

public interface IMultiReferenceTool extends IFieldTool {

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);

  boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  boolean canClear(IMultiReference<?> multiReference);
}
