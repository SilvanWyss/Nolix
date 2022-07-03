//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ITableMapper<IMPL> {
	
	//method declaration
	IContainer<ITable<IMPL>> createTablesFrom(ISchema<?> schema);
}
