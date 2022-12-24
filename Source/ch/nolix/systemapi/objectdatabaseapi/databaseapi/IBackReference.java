//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBackReference<
	IMPL,
	E extends IEntity<IMPL>
> 
extends IBaseBackReference<IMPL, E> {
	
	//method declaration
	E getBackReferencedEntity();
	
	//method declaration
	String getBackReferencedEntityId();
	
	//method declaration
	boolean referencesBackEntity();
}
