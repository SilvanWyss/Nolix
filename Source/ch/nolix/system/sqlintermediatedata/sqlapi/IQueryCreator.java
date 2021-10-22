//package declaration
package ch.nolix.system.sqlintermediatedata.sqlapi;

//interface
public interface IQueryCreator {
	
	//method declaration
	String createQueryToCountRecordsWithGivenValueAtGivenColumn(String tableName, String columnHeader, String value);
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(ITableDefinitionDTO tableDefinition);
	
	//method declaration
	String createQueryToLoadRecordFromTableById(String id, ITableDefinitionDTO tableDefinition);
}
