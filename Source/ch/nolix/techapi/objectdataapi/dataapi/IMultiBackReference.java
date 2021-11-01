//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiBackReference<
	MBR extends IMultiBackReference<MBR, E>,
	E extends IEntity<E, MBR>
> extends IContainer<E>, IProperty<MBR> {}
