//package declaration
package ch.nolix.techapi.intermediatedataapi.dataadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataWriter extends IChangeSaver {
	
	//method declaration
	void addRecordToTable(String tableName, IRecordDTO record);
	
	//method declaration
	void deleteRecordFromTable(String tableName, IRecordDTO record);
	
	//method declaration
	void editRecordOnTable(String tableName, IRecordDTO record);
}
