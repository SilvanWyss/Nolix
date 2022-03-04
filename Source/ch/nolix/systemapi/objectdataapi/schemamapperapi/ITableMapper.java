//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ITableMapper<IMPL> {
	
	//method declaration
	ITable<IMPL> createTableFrom(Class<IEntity<?>> entityType);
	
	//method declaration
	ITable<IMPL> createTableFrom(IEntity<?> entity);
	
	//method declaration
	LinkedList<ITable<IMPL>> createTablesFrom(ISchema<?> schema);
}
