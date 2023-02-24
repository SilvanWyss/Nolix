//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

//interface
public interface IPropertyValidator {
	
	//method declaration
	void assertBelongsToEntity(IProperty property);
	
	//method declaration
	void assertDoesNotBelongToEntity(IProperty property);
	
	//method declaration
	void assertIsNotEmpty(IProperty property);
	
	//method declaration
	void assertIsNotMandatoryAndEmptyBoth(IProperty property);
	
	//method declaration
	void assertKnowsParentColumn(IProperty property);
}
