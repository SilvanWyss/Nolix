//package declaration
package ch.nolix.systemapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IReferenceHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenEntity(IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	boolean canSetGivenEntity(final IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetEntity(IReference<?, ?> reference, IEntity<?> entity);
}
