//package declaration
package ch.nolix.systemapi.objectdatabaseapi.schemamapperapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
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
	<E extends IEntity<?>> IContainer<IColumn<IMPL>> createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
		Class<E> entityType,
		IContainer<ITable<IMPL>> referencableTables
	);
	
	//method declaration
	IContainer<IColumn<IMPL>> createColumnsFromGivenEntityUsingGivenReferencableTables(
		IEntity<?> entity,
		IContainer<ITable<IMPL>> referencableTables
	);
}
