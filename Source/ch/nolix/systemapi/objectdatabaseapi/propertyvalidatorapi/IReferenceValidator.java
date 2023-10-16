//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;

//interface
public interface IReferenceValidator extends IPropertyValidator {

  //method declaration
  void assertCanSetGivenEntity(IReference<?> reference, IEntity entity);
}
