//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

//own imports
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IRecordStatementCreator {
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IEntityHeadDTO entity);
	
	//method
	String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);
	
	//method declaration
	String createStatementToInsertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToSetEntityAsUpdated(String tableName, IEntityHeadDTO entity);
	
	//method declaration
	String createStatementToUpdateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
