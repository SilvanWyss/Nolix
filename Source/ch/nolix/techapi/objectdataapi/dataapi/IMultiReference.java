//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiReference<
	P extends IProperty<P>,
	E extends IEntity<E, P>
> extends IContainer<E>, IBaseReference<P, E> {}
