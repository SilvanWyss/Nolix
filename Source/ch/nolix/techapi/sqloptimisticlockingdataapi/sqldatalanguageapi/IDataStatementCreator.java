//package declaration
package ch.nolix.techapi.sqloptimisticlockingdataapi.sqldatalanguageapi;

import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataStatementCreator {
	
	//method declaration
	String creatStatementToAddRecordToTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToEditRecordOnTable(String tableName, IRecordDTO record);
}
