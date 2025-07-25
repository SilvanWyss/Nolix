package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public interface IMultiReferenceTool extends IFieldTool {

  <E extends IEntity> boolean canAddEntity(IMultiReference<E> multiReference);

  <E extends IEntity> boolean canAddEntity(IMultiReference<E> multiReference, IEntity entity);

  <E extends IEntity> boolean canClear(IMultiReference<E> multiReference);

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference);

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);
}
