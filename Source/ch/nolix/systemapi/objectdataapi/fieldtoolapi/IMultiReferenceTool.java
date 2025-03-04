package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.fieldapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IMultiReferenceTool extends IFieldTool {

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);

  boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  boolean canClear(IMultiReference<?> multiReference);
}
