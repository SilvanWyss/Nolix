//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IRecordStatementCreator {
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IEntityHeadDTO entity);
	
	//method
	String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);
	
	//method declaration
	String createStatementToInsertRecordIntoTable(String tableName, IRecordDTO pRecord);
	
	//method declaration
	String createStatementToSetEntityAsUpdated(String tableName, IEntityHeadDTO entity);
	
	//method declaration
	String createStatementToUpdateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
