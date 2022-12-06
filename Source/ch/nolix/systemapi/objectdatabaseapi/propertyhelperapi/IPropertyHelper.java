//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

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
	void assertKnowsParentColumn(IProperty<?> property);
	
	//method declaration
	boolean belongsToLoadedEntity(IProperty<?> property);
	
	//method declaration
	Class<?> getDataType(IProperty<?> property);
	
	//method declaration
	boolean isForMultiContent(IProperty<?> property);
	
	//method declaration
	boolean isForSingleContent(IProperty<?> property);
	
	//method declaration
	boolean isMandatoryAndEmptyBoth(IProperty<?> property);
		
	//method declaration
	boolean isSetForCaseIsNewOrEditedAndMandatory(IProperty<?> property);
}
