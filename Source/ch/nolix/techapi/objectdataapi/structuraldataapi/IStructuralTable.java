//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface IStructuralTable<
	ST extends IStructuralTable<ST, SE, P>,
	SE extends IStructuralEntity<SE, P>,
	P extends IProperty<P>
> extends ITable<ST, SE> {}
