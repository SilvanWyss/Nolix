//package declaration
package ch.nolix.system.sqlintermediatedata.sqlapi;

//own imports
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ITableDefinitionDTO;

//interface
public interface IQueryCreator {
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(ITableDefinitionDTO tableDefinition);
	
	//method declaration
	String createQueryToLoadRecordFromTableById(String id, ITableDefinitionDTO tableDefinition);
}
