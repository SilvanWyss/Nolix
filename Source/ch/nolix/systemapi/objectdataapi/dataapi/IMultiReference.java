//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.skillapi.Clearable;

//interface
public interface IMultiReference<
	IMPL,
	E extends IEntity<IMPL>
> extends Clearable, IContainer<E>, IBaseReference<IMPL, E> {
	
	//method declaration
	void addEntity(E entity);
	
	//method
	@Override
	default boolean containsAny() {
		return IContainer.super.containsAny();
	}
	
	//method declaration
	IContainer<String> getReferencedEntityIds();
	
	//method
	@Override
	default boolean isEmpty() {
		return IContainer.super.isEmpty();
	}
}
