//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//interface
public interface IOptionalReferenceHelper {
			
	//method declaration
	boolean canClear(IOptionalReference<?> optionalReference);
	
	//method declaration
	boolean canSetGivenEntity(final IOptionalReference<?> optionalReference, IEntity entity);
	
	//method
	IEntityUpdateDTO createEntityUpdateDTOForClear(IOptionalReference<?> optionalReference);
	
	//method declaration
	IEntityUpdateDTO createEntityUpdateDTOForSetEntity(IOptionalReference<?> optionalReference, IEntity entity);
	
	//method declaration
	 IProperty getRefBackReferencingPropertyOrNull(IOptionalReference<?> optionalReference);
}
