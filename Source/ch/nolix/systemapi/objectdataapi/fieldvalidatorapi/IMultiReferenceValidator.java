//package declaration
package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;

//interface
public interface IMultiReferenceValidator extends IFieldValidator {

  //method declaration
  void assertCanAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  //method declaration
  void assertCanClear(IMultiReference<?> multiReference);

  //method declaration
  <E extends IEntity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
