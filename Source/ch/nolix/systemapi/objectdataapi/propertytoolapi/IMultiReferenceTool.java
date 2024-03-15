//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IMultiReferenceTool extends IPropertyTool {

  //method declaration
  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForAddEntity(IMultiReference<?> multiReference, IEntity entity);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForClear(IMultiReference<?> multiReference);

  //method declaration
  boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  //method declaration
  boolean canClear(IMultiReference<?> multiReference);
}
