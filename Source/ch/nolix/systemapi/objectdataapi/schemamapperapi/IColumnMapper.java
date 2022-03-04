//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IColumnMapper<IMPL> {
	
	//method declaration
	IColumn<IMPL> createColumnFromGivenPropertyUsingGivenReferencableTables(
		IProperty<?> property,
		IContainer<ITable<IMPL>> referencableTables
	);
	
	//method declaration
	<E extends IEntity<?>> LinkedList<IColumn<IMPL>> createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
		Class<E> entityType,
		IContainer<ITable<IMPL>> referencableTables
	);
	
	//method declaration
	LinkedList<IColumn<IMPL>> createColumnsFromGivenEntityUsingGivenReferencableTables(
		IEntity<?> entity,
		IContainer<ITable<IMPL>> referencableTables
	);
}
