//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

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
