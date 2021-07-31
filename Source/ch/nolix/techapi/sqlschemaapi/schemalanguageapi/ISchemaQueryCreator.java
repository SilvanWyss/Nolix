//package declaration
package ch.nolix.techapi.sqlschemaapi.schemalanguageapi;

//interface
public interface ISchemaQueryCreator {
	
	//method declaration
	String createQueryToLoadNameAndDataTypeOfColumns(String tableName);
	
	//method declaration
	String createQueryToLoadNameOfTables();
}
