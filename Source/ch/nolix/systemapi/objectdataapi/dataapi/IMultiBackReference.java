//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.container.IContainer;

//interface
public interface IMultiBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IContainer<E>, IBaseBackReference<IMPL, E> {
	
	//method
	@Override
	default boolean containsAny() {
		return IContainer.super.containsAny();
	}
	
	//method
	@Override
	default boolean isEmpty() {
		return IContainer.super.isEmpty();
	}
}
