//package declaration
package ch.nolix.system.sqlintermediatedata.sqlapi;

//interface
public interface IQueryCreator {
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(ITableDefinitionDTO tableDefinition);
	
	//method declaration
	String createQueryToLoadRecordFromTableById(String id, ITableDefinitionDTO tableDefinition);
}
