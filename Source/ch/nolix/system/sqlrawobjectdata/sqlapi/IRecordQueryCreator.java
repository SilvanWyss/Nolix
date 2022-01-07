//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//interface
public interface IRecordQueryCreator {
	
	//method declaration
	String createQueryToCountRecordsWithGivenValueAtGivenColumn(String tableName, String columnName, String value);
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(ITableDefinition tableDefinition);
	
	//method declaration
	String createQueryToLoadRecordFromTableById(String id, ITableDefinition tableDefinition);
}
