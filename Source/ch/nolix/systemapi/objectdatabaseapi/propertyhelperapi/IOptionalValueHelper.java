//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//interface
public interface IOptionalValueHelper extends IPropertyHelper {
	
	//method declaration
	void assertHasValue(IOptionalValue<?, ?> optionalValue);
	
	//method declaration
	boolean canSetGivenValue(IOptionalValue<?, ?> optionalValue, Object value);
	
	//method declaration
	IEntityUpdateDTO createRecordUpdateDTOForSetValue(IOptionalValue<?, ?> optionalValue, Object value);
}
