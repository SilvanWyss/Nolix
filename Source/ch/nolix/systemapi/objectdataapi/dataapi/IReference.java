//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IBaseReference<IMPL, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	void setEntity(final E entity);
}
