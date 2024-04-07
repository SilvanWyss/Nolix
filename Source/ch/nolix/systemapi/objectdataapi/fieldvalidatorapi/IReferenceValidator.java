//package declaration
package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;

//interface
public interface IReferenceValidator extends IFieldValidator {

  //method declaration
  void assertCanSetGivenEntity(IReference<?> reference, IEntity entity);
}
