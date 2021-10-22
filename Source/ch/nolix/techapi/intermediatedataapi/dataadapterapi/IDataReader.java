//package declaration
package ch.nolix.techapi.intermediatedataapi.dataadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ILoadedRecordDTO;

//interface
public interface IDataReader {
	
	//method declaration
	LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(String tableName);
	
	//method declaration
	ILoadedRecordDTO loadRecordFromTableById(String tableName, String id);
	
	//method declaration
	boolean tableContainsRecordWithGivenValueAtColumn(String tableName, String columnHeader, String value);
}
