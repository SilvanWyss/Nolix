//package declaration
package ch.nolix.techapi.intermediatedataapi.dataadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordUpdateDTO;

//interface
public interface IDataWriter extends IChangeSaver {
	
	//method declaration
	void deleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion);
	
	//method declaration
	void insertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
