//package declaration
package ch.nolix.systemapi.rawobjectdataapi.dataadapterapi;

//own imports
import ch.nolix.core.skillapi.IChangeSaver;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
public interface IDataWriter extends IChangeSaver {
	
	//method declaration
	void deleteEntriesFromMultiValue(String tableName, IRecordHeadDTO recordHead, String multiValueColumnName);
	
	//method declaration
	void deleteEntryFromMultiValue(
		String tableName,
		IRecordHeadDTO recordHead,
		String multiValueColumnName,
		String entry
	);
	
	//method declaration
	void deleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion);
	
	//method declaration
	void insertEntryIntoMultiValue(String tableName, IRecordHeadDTO recordHead, String multiValueColumnName, String entry);
	
	//method declaration
	void insertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
