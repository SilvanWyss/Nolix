//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;

//interface
public interface IValueValidator extends IPropertyValidator {

  //method declaration
  void assertCanSetGivenValue(IValue<?> value, Object valueToSet);
}
