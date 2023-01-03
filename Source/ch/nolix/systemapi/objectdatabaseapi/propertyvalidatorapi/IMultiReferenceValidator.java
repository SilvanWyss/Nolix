//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;

//interface
public interface IMultiReferenceValidator extends IPropertyValidator {
	
	//method declaration
	void assertCanAddGivenEntity(IMultiReference<?, ?> multiReference, IEntity<?> entity);
	
	//method declaration
	void assertCanClear(IMultiReference<?, ?> multiReference);
		
	//method declaration
	<IMPL, E extends IEntity<IMPL>> void assertCanRemoveEntity(IMultiReference<IMPL, E> multiReference, E entity);
}
