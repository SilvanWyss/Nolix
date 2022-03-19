//package declaration
package ch.nolix.systemapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

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
