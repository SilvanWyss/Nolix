//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;

//interface
public interface IOptionalValueValidator extends IPropertyValidator {

  //method declaration
  <V> void assertCanSetGivenValue(IOptionalValue<V> optionalValue, final V value);

  //method declaration
  void assertHasValue(IOptionalValue<?> optionalValue);
}
