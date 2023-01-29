//package declaration
package ch.nolix.systemapi.objectdatabaseapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ITableMapper<IMPL> {
	
	//method declaration
	IContainer<ITable<IMPL>> createEmptyTablesFromSchema(final ISchema<?> schema);
	
	//method declaration
	IContainer<ITable<IMPL>> createTablesFrom(ISchema<?> schema);
}
