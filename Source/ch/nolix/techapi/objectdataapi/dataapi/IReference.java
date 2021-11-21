//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IBaseReference<IMPL, E> {
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesEntity();
	
	//method declaration
	IReference<IMPL, E> setEntity(final E entity);
}
