//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalValue;

//interface
public interface IOptionalValueHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertHasValue(IOptionalValue<?, ?> optionalValue);
}
