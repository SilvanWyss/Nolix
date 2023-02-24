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
	IProperty getRefBackReferencingPropertyOrNull();
	
	//method declaration
	E getReferencedEntity();
	
	//method declaration
	String getReferencedEntityId();
	
	//method declaration
	IMultiReference<E> getRefParentMultiReference();
}
