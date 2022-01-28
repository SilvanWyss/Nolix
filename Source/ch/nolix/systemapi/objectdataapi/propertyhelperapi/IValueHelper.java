//package declaration
package ch.nolix.systemapi.objectdataapi.propertyhelperapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IValueHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	boolean canSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetValue(IValue<?, ?> value, Object valueToSet);
}
