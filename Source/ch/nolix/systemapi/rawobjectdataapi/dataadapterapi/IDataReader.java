//package declaration
package ch.nolix.systemapi.rawobjectdataapi.dataadapterapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//interface
public interface IDataReader extends GroupCloseable {
	
	//method declaration
	LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(String tableName);
	
	//method declaration
	LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		String tableName,
		String entityId,
		String multiReferenceColumnName
	);
	
	//method declaration
	LinkedList<Object> loadAllMultiValueEntriesFromRecord(
		String tableName,
		String entityId,
		String multiValueColumnName
	);
	
	//method declaration
	ILoadedRecordDTO loadRecordFromTableById(String tableName, String id);
	
	//method declaration
	boolean tableContainsRecordWithGivenValueAtColumn(String tableName, String columnName, String value);
}
