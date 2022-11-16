//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IOptionalReferenceHelper {
	
	//method declaration
	void assertCanClear(IOptionalReference<?, ?> optionalReference);
	
	//method declaration
	void assertCanSetGivenEntity(IOptionalReference<?, ?> optionalReference, IEntity<?> entity);
	
	//method declaration
	void assertIsNotEmpty(IOptionalReference<?, ?> optionalReference);
	
	//method declaration
	boolean canClear(IOptionalReference<?, ?> optionalReference);
	
	//method declaration
	boolean canSetGivenEntity(final IOptionalReference<?, ?> optionalReference, IEntity<?> entity);
	
	//method
	IRecordUpdateDTO createRecordUpdateDTOForClear(IOptionalReference<?, ?> optionalReference);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetEntity(IOptionalReference<?, ?> optionalReference, IEntity<?> entity);
}
