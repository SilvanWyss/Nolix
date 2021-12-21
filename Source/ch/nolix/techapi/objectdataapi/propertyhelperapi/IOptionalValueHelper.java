//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IOptionalValueHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertHasValue(IOptionalValue<?, ?> optionalValue);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForClear(IOptionalValue<?, ?> optionalValue);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetValue(IOptionalValue<?, ?> optionalValue, Object value);
}
