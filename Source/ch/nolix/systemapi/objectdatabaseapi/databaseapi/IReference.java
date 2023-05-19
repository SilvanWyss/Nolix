//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IReference<

	E extends IEntity
>
extends IBaseReference<E> {
	
	//method declaration
	E getOrierencedEntity();
	
	//method declaration
	String getOrierencedEntityId();
	
	//method declaration
	void setEntity(final E entity);
	
	//method declaration
	void setEntityById(final String id);
}
