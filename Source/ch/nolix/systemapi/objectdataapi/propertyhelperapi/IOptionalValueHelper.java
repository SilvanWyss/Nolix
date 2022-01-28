//package declaration
package ch.nolix.systemapi.objectdataapi.propertyhelperapi;

import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IOptionalValueHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertCanSetGivenValue(IOptionalValue<?, ?> optionalValue, final Object value);
	
	//method declaration
	void assertHasValue(IOptionalValue<?, ?> optionalValue);
	
	//method declaration
	boolean canSetGivenValue(IOptionalValue<?, ?> optionalValue, Object value);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForClear(IOptionalValue<?, ?> optionalValue);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetValue(IOptionalValue<?, ?> optionalValue, Object value);
}
