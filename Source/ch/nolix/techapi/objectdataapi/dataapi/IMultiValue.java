//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiValue<
	IMPL,
	V
> extends IContainer<V>, IBaseValue<IMPL, V> {}
