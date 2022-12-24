//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IMultiValueEntry<
	IMPL,
	V
>
extends IDatabaseObject {
	
	//method declaration
	IMultiValue<IMPL, V> getRefParentMultiValue();
	
	//method declaration
	V getRefValue();
}
