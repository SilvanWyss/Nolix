//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.skillapi.Clearable;

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
	
	//method
	@Override
	default boolean isEmpty() {
		return IContainer.super.isEmpty();
	}
}
