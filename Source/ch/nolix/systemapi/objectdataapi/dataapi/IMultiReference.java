//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IMultiReference<
	IMPL,
	E extends IEntity<IMPL>
> extends Clearable, IBaseReference<IMPL, E> {
	
	//method declaration
	void addEntity(E entity);
	
	//method declaration
	IContainer<E> getReferencedEntities();
	
	//method declaration
	IContainer<String> getReferencedEntityIds();
}
