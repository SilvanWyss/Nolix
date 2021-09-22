//package declaration
package ch.nolix.techapi.intermediatedataapi.sqldatalanguageapi;

//own imports
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataStatementCreator {
	
	//method declaration
	String creatStatementToAddRecordToTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToDeleteRecordFromTable(String tableName, IRecordDTO record);
	
	//method declaration
	String createStatementToEditRecordOnTable(String tableName, IRecordDTO record);
}
