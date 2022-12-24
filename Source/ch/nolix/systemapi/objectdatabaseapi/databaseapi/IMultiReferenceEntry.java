//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IMultiReferenceEntry<
	IMPL,
	E extends IEntity<IMPL>
>
extends IDatabaseObject {
	
	//method declaration
	IProperty<IMPL> getRefBackReferencingPropertyOrNull();
	
	//method declaration
	E getReferencedEntity();
	
	//method declaration
	String getReferencedEntityId();
	
	//method declaration
	IMultiReference<IMPL, E> getRefParentMultiReference();
}
