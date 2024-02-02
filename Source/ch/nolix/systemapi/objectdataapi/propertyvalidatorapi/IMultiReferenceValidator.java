//package declaration
package ch.nolix.systemapi.objectdataapi.propertyvalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;

//interface
public interface IMultiReferenceValidator extends IPropertyValidator {

  //method declaration
  void assertCanAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  //method declaration
  void assertCanClear(IMultiReference<?> multiReference);

  //method declaration
  <E extends IEntity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
