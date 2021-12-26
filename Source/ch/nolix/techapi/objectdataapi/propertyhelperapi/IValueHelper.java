//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IValue;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IValueHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	boolean canSetGivenValue(IValue<?, ?> value, Object valueToSet);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetValue(IValue<?, ?> value, Object valueToSet);
}
