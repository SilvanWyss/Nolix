package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

public interface IMultiReferenceTool extends IFieldTool {

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);

  EntityUpdateDto createEntityUpdateDtoForAddEntity(IMultiReference<?> multiReference, IEntity entity);

  EntityUpdateDto createEntityUpdateDtoForClear(IMultiReference<?> multiReference);

  boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  boolean canClear(IMultiReference<?> multiReference);
}
