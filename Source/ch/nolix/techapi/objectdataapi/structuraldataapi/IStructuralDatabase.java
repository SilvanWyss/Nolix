//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IStructuralDatabase<
	SD extends IStructuralDatabase<SD, ST, SE, P>,
	ST extends IStructuralTable<ST, SE, P>,
	SE extends IStructuralEntity<SE, P>,
	P extends IProperty<P>
> extends IDatabase<SD, ST, SE> {}
