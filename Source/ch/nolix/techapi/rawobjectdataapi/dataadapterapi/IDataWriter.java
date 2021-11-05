//package declaration
package ch.nolix.techapi.rawobjectdataapi.dataadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IDataWriter extends IChangeSaver {
	
	//method declaration
	void deleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion);
	
	//method declaration
	void insertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
