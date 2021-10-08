//package declaration
package ch.nolix.system.sqlintermediatedata.sqlapi;

//own imports
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordUpdateDTO;

//interface
public interface IStatementCreator {
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion);
	
	//method declaration
	String createStatementToInsertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToUpdateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
