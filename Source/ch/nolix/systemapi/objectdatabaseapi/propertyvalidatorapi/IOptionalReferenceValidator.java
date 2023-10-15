//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;

//interface
public interface IOptionalReferenceValidator extends IPropertyValidator {

  // method declaration
  void assertCanClear(IOptionalReference<?> optionalReference);

  // method declaration
  void assertCanSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  // method declaration
  void assertIsNotEmpty(IOptionalReference<?> optionalReference);
}
