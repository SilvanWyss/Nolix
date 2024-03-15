//package declaration
package ch.nolix.systemapi.objectdataapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;

//interface
public interface IValueValidator extends IPropertyValidator {

  //method declaration
  void assertCanSetGivenValue(IValue<?> value, Object valueToSet);
}
