//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IValue;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IValueHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertCanSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	void assertHasValue(IValue<?, ?> value);
	
	//method declaration
	boolean canSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetValue(IValue<?, ?> value, Object valueToSet);
}
