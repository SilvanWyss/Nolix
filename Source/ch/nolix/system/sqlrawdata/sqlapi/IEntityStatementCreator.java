//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

//own imports
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IEntityStatementCreator {
	
	//method declaration
	String createStatementToDeleteEntity(String tableName, IEntityHeadDTO entity);
	
	//method
	String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);
	
	//method
	String createStatementToExpectTableContainsEntity(String tableName, String entityId);
	
	//method declaration
	String createStatementToInsertNewEntity(String tableName, INewEntityDTO newEntity);
	
	//method declaration
	String createStatementToSetEntityAsUpdated(String tableName, IEntityHeadDTO entity);
	
	//method declaration
	String createStatementToUpdateEntityOnTable(String tableName, IEntityUpdateDTO entityUpdate);
}
