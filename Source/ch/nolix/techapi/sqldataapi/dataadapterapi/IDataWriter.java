//package declaration
package ch.nolix.techapi.sqldataapi.dataadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.sqldataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataWriter extends IChangeSaver {
	
	//method declaration
	void deleteRecordFromTable(String tableName, IRecordDTO record);
	
	//method declaration
	void insertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	void updateRecordOnTable(String tableName, IRecordDTO record);
}
