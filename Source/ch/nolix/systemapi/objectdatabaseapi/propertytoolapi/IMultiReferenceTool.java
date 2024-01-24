//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertytoolapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

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