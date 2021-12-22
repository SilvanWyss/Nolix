//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.system.objectdata.data.MultiValue;
import ch.nolix.techapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IMultiValueHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanAddGivenValue(IMultiValue<?, ?> multiValue, Object value);
	
	//method declaration
	void assertCanClear(IMultiValue<?, ?> multiValue);
	
	//method declaration
	boolean canAddGivenValue(IMultiValue<?, ?> multiValue, Object value);
	
	//method declaration
	boolean canClear(IMultiValue<?, ?> multiValue);
	
	//method declaration
	<V> IRecordUpdateDTO createRecordUpdateDTOForAddedValue(IMultiValue<?, V> multiValue, V addedValue);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForClear(MultiValue<?> multiValue);
}