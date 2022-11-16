//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;

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
