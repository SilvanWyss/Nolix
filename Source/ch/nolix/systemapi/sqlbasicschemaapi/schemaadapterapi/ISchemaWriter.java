//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi;

import ch.nolix.core.skilluniversalapi.IMultiTimeChangeSaver;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaWriter extends IMultiTimeChangeSaver {
	
	//method declaration
	void addColumn(String tableName, IColumnDTO column);
	
	//method declaration
	void addTable(ITableDTO table);
	
	//method declaration
	void deleteColumn(String tableName, String columnName);
	
	//method declaration
	void deleteTable(String tableName);
	
	//method declaration
	IContainer<String> getSQLStatements();
	
	//method declaration
	void renameColumn(String tableName, String columnName, String newColumnName);
	
	//method declaration
	void renameTable(String tableName, String newTableName);
}
