//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiBackReference<
	MBR extends IMultiBackReference<MBR, SE>,
	SE extends IStructuralEntity<SE, MBR>
> extends IContainer<SE>, IProperty<MBR> {}
