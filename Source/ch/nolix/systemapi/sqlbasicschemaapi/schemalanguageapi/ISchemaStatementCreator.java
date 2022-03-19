//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemalanguageapi;

//own imports
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaStatementCreator {
	
	//method declaration
	String createStatementToAddColumn(String tableName, IColumnDTO column);
	
	//method declaration
	String createStatementToAddTable(ITableDTO table);
	
	//method declaration
	String createStatementToDeleteColumn(String tableName, String columnName);
	
	//method declaration
	String createStatementToDeleteTable(String tableName);
	
	//method declaration
	String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);
	
	//method declaration
	String createStatementToRenameTable(String tableName, String newTableName);
}
