//package declaration
package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;

//interface
public interface IValueValidator extends IFieldValidator {

  //method declaration
  void assertCanSetGivenValue(IValue<?> value, Object valueToSet);
}
