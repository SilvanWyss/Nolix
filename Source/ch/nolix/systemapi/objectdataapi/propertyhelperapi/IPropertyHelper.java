//package declaration
package ch.nolix.systemapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;

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
