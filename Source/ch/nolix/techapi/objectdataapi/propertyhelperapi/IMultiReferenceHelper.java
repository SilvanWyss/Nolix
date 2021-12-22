//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IMultiReferenceHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanAddGivenEntity(IMultiReference<?, ?> multiReference, IEntity<?> entity);
	
	//method declaration
	void assertCanClear(IMultiReference<?, ?> multiReference);
	
	//method declaration
	IRecordUpdateDTO createRecordupdateDTOForAddEntity(IMultiReference<?, ?> multiReference, IEntity<?> entity);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForClear(IMultiReference<?, ?> multiReference);
	
	//method declaration
	boolean canAddGivenEntity(IMultiReference<?, ?> multiReference, IEntity<?> entity);
	
	//method declaration
	boolean canClear(IMultiReference<?, ?> multiReference);
}
