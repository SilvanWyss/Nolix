//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiBackReference<
	P extends IProperty<P>,
	E extends IEntity<E, P>
> extends IContainer<E>, IBaseBackReference<P, E> {}
