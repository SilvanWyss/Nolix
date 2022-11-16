//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;

//interface
public interface IValueHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	boolean canSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetValue(IValue<?, ?> value, Object valueToSet);
}
