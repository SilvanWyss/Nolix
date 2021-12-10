//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.skillapi.Clearable;

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
