//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.skilluniversalapi.Clearable;

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
