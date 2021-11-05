//package declaration
package ch.nolix.techapi.rawobjectdataapi.dataadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//interface
public interface IDataReader {
	
	//method declaration
	LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(String tableName);
	
	//method declaration
	ILoadedRecordDTO loadRecordFromTableById(String tableName, String id);
	
	//method declaration
	boolean tableContainsRecordWithGivenValueAtColumn(String tableName, String columnHeader, String value);
}
