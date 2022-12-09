//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IOptionalBackReference<
	IMPL,
	E extends IEntity<IMPL>
>
extends IBaseBackReference<IMPL, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesBackEntity();
}
