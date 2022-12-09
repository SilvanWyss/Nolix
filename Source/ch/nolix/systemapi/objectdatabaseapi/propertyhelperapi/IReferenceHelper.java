//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//interface
public interface IReferenceHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenEntity(IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	boolean canSetGivenEntity(final IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	IEntityUpdateDTO createRecordUpdateDTOForSetEntity(IReference<?, ?> reference, IEntity<?> entity);
	
	//method declaration
	<IMPL> IProperty<IMPL> getRefBackReferencingPropertyOrNull(IReference<IMPL, ?> reference);
}
