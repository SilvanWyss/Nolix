//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IRecordUpdateDTO;

//interface
public interface IStatementCreator {
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion);
	
	//method declaration
	String createStatementToInsertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToUpdateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
