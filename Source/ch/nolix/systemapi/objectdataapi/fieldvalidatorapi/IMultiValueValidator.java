//package declaration
package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;

//interface
public interface IMultiValueValidator extends IFieldValidator {

  //method declaration
  void assertCanAddGivenValue(IMultiValue<?> multiValue, Object value);

  //method declaration
  void assertCanClear(IMultiValue<?> multiValue);

  //method declaration
  <V> void assertCanRemoveValue(IMultiValue<V> multiValue, V value);
}
