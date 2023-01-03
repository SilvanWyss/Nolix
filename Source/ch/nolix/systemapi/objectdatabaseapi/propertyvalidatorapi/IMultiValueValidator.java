//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;

//interface
public interface IMultiValueValidator {
	
	//method declaration
	void assertCanAddGivenValue(IMultiValue<?, ?> multiValue, Object value);
	
	//method declaration
	void assertCanClear(IMultiValue<?, ?> multiValue);
}
