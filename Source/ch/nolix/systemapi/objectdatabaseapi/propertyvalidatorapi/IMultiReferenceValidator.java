//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;

//interface
public interface IMultiReferenceValidator extends IPropertyValidator {

  // method declaration
  void assertCanAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  // method declaration
  void assertCanClear(IMultiReference<?> multiReference);

  // method declaration
  <E extends IEntity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
