//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//interface
public interface IQueryCreator {
	
	//method declaration
	String createQueryToCountRecordsWithGivenValueAtGivenColumn(String tableName, String columnHeader, String value);
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(ITableDefinition tableDefinition);
	
	//method declaration
	String createQueryToLoadRecordFromTableById(String id, ITableDefinition tableDefinition);
}
