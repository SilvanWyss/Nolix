//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IContainer<E>, IBaseBackReference<IMPL, E> {}
