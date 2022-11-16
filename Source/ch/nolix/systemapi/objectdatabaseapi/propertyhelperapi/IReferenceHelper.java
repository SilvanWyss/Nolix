//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;

//interface
public interface IReferenceHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenEntity(IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	boolean canSetGivenEntity(final IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetEntity(IReference<?, ?> reference, IEntity<?> entity);
}
