//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiReference<
	MR extends IMultiReference<MR, E>,
	E extends IEntity<E, MR>
> extends IContainer<E>, IProperty<MR> {}
