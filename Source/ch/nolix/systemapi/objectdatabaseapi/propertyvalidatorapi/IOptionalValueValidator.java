//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;

//interface
public interface IOptionalValueValidator {
	
	//method declaration
	<V> void assertCanSetGivenValue(IOptionalValue<?, V> optionalValue, final V value);
}
