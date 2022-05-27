//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.skilluniversalapi.Clearable;

//interface
public interface IMultiValue<
	IMPL,
	V
> extends Clearable, IBaseValue<IMPL, V>, IContainer<V> {
	
	//method declaration
	void addValue(V value);
	
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
