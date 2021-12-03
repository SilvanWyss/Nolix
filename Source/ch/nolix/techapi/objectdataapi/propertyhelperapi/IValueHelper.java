//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IValue;

//interface
public interface IValueHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertHasValue(IValue<?, ?> value);
}
