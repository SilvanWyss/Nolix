//package declaration
package ch.nolix.techapi.sqlschemaapi.schemaadapterapi;

//own imports
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaWriter {
	
	//method declaration
	void addColumn(String tableName, IColumnDTO column);
	
	//method declaration
	void addTable(ITableDTO table);
	
	//method declaration
	void deleteColumn(String tableName, String columnName);
	
	//method declaration
	void deleteTable(String tableName);
	
	//method declaration
	void renameColumn(String tableName, String columnName, String newColumnName);
	
	//method declaration
	void renameTable(String tableName, String newTableName);
}
