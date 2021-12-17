//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalReference;

//interface
public interface IOptionalReferenceHelper {
	
	//method declaration
	void assertIsNotEmpty(IOptionalReference<?, ?> optionalReference);
}
