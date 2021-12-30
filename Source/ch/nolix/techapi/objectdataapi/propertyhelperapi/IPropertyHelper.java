//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;

//interface
public interface IPropertyHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertBelongsToEntity(IProperty<?> property);
	
	//method declaration
	void assertDoesNotBelongToEntity(IProperty<?> property);
	
	//method declaration
	void assertIsNotEmpty(IProperty<?> property);
	
	//method declaration
	void assertIsNotMandatoryAndEmptyBoth(IProperty<?> property);
	
	//method declaration
	IContentFieldDTO createContentFieldFor(IProperty<?> property);
	
	//method declaration
	boolean isMandatoryAndEmptyBoth(IProperty<?> property);
}
