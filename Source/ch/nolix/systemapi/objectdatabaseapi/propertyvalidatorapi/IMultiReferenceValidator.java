//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;

//interface
public interface IMultiReferenceValidator {
	
	//method declaration
	<IMPL, E extends IEntity<IMPL>> void assertCanRemoveEntity(IMultiReference<IMPL, E> multiReference, E entity);
}
