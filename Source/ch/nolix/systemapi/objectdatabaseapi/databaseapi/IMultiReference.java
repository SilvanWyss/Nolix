//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IMultiReference<
	IMPL,
	E extends IEntity<IMPL>
>
extends Clearable, IBaseReference<IMPL, E> {
	
	//method declaration
	void addEntity(E entity);
	
	//method declaration
	IContainer<E> getReferencedEntities();
	
	//method declaration
	IContainer<String> getReferencedEntityIds();
	
	//method declaration
	IContainer<? extends IMultiReferenceEntry<IMPL, E>> getRefLocalEntries();
	
	//method declaration
	void removeEntity(E entity);
}
