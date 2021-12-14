//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IReference;

//interface
public interface IReferenceHelper extends IPropertyHelper {
	
	//method declaration
	void assertReferencesEntity(IReference<?, ?> referenc);
}
