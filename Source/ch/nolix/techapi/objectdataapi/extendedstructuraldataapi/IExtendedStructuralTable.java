//package declaration
package ch.nolix.techapi.objectdataapi.extendedstructuraldataapi;

//own imports
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedTable;
import ch.nolix.techapi.objectdataapi.structuraldataapi.IProperty;
import ch.nolix.techapi.objectdataapi.structuraldataapi.IStructuralTable;

//interface
public interface IExtendedStructuralTable<
	EST extends IExtendedStructuralTable<EST, ESE, P>,
	ESE extends IExtendedStructuralEntity<ESE, P>,
	P extends IProperty<P>
> extends IExtendedTable<EST, ESE>, IStructuralTable<EST, ESE, P> {}
