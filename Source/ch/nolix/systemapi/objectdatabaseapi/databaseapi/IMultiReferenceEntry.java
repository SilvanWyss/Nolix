//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IMultiReferenceEntry<

	E extends IEntity
>
extends IDatabaseObject {
	
	//method declaration
	IProperty getOriBackReferencingPropertyOrNull();
	
	//method declaration
	E getOrierencedEntity();
	
	//method declaration
	String getOrierencedEntityId();
	
	//method declaration
	IMultiReference<E> getOriParentMultiReference();
}
