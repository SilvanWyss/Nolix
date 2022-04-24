//package declaration
package ch.nolix.systemapi.rawdataapi.dataadapterapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;

//interface
public interface IDataReader extends GroupCloseable {
	
	//method
	Time getSchemaTimestamp();
	
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
	boolean tableContainsEntityWithGivenValueAtGivenColumn(String tableName, String columnName, String value);
}
