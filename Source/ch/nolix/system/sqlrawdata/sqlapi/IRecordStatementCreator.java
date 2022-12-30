//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IRecordStatementCreator {
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IEntityHeadDTO entity);
	
	//method
	String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);
	
	//method
	String createStatementToExpectTableContainsEntity(String tableName, String entityId);
	
	//method declaration
	String createStatementToInsertRecordIntoTable(String tableName, INewEntityDTO pRecord);
	
	//method declaration
	String createStatementToSetEntityAsUpdated(String tableName, IEntityHeadDTO entity);
	
	//method declaration
	String createStatementToUpdateEntityOnTable(String tableName, IEntityUpdateDTO recordUpdate);
}
