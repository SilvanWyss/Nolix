//package declaration
package ch.nolix.systemapi.objectdataapi.propertyvalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;

//interface
public interface IOptionalValueValidator extends IPropertyValidator {

  //method declaration
  <V> void assertCanSetGivenValue(IOptionalValue<V> optionalValue, V value);

  //method declaration
  void assertHasValue(IOptionalValue<?> optionalValue);
}
