//package declaration
package ch.nolix.systemapi.rawobjectdataapi.dataadapterapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//interface
public interface IDataReader {
	
	//method declaration
	LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(String tableName);
	
	//method declaration
	LinkedList<Object> loadMultiValueEntriesFromRecord(String tableName, String recordId, String multiValueColumnName);
	
	//method declaration
	ILoadedRecordDTO loadRecordFromTableById(String tableName, String id);
	
	//method declaration
	boolean tableContainsRecordWithGivenValueAtColumn(String tableName, String columnName, String value);
}
