//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//own imports
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IRecordStatementCreator {
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion);
	
	//method declaration
	String createStatementToInsertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToUpdateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
