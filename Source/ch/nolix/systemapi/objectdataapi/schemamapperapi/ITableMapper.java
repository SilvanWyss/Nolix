//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ITableMapper<IMPL> {
	
	//method declaration
	LinkedList<ITable<IMPL>> createTablesFrom(ISchema<?> schema);
}
