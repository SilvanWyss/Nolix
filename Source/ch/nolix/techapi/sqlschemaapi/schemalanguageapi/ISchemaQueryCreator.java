//package declaration
package ch.nolix.techapi.sqlschemaapi.schemalanguageapi;

//interface
public interface ISchemaQueryCreator {
	
	//method declaration
	String createQueryToLoadNameAndDataTypeOfColumns(String tableName);
	
	//method declaration
	String createQueryToLoadNameOfTables();
	
	//method declaration
	String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
