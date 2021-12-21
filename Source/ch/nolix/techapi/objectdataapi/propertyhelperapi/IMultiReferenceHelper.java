//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IMultiReferenceHelper extends IPropertyHelper {
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForClear(IMultiReference<?, ?> multiReference);
}
