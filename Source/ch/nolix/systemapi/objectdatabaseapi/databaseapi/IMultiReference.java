//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IMultiReference<

	E extends IEntity
>
extends Clearable, IBaseReference<E> {
	
	//method declaration
	void addEntity(E entity);
	
	//method declaration
	IContainer<E> getOrierencedEntities();
	
	//method declaration
	IContainer<String> getOrierencedEntityIds();
	
	//method declaration
	IContainer<? extends IMultiReferenceEntry<E>> getOriLocalEntries();
	
	//method declaration
	void removeEntity(E entity);
}
