//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IOptionalValueHelper extends IPropertyHelper {
	
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
