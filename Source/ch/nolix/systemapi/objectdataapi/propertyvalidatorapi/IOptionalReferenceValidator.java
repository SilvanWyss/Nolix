//package declaration
package ch.nolix.systemapi.objectdataapi.propertyvalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;

//interface
public interface IOptionalReferenceValidator extends IPropertyValidator {

  //method declaration
  void assertCanClear(IOptionalReference<?> optionalReference);

  //method declaration
  void assertCanSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  //method declaration
  void assertIsNotEmpty(IOptionalReference<?> optionalReference);
}
