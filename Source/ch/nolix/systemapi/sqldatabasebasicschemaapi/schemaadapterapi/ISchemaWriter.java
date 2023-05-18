//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;

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
