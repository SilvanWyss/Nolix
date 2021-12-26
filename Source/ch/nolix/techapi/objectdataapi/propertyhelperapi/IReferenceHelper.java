//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IReference;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IReferenceHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenEntity(IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	boolean canSetGivenEntity(final IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	IRecordUpdateDTO createRecordUpdateDTOForSetEntity(IReference<?, ?> reference, IEntity<?> entity);
}
