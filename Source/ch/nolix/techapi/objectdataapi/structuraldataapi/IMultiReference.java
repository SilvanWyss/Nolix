//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiReference<
	MR extends IMultiReference<MR, SE>,
	SE extends IStructuralEntity<SE, MR>
> extends IContainer<SE>, IProperty<MR> {}
