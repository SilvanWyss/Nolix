//package declaration
package ch.nolix.systemapi.objectdataapi.propertyvalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;

//interface
public interface IReferenceValidator extends IPropertyValidator {

  //method declaration
  void assertCanSetGivenEntity(IReference<?> reference, IEntity entity);
}
