//package declaration
package ch.nolix.systemapi.rawdataapi.dataadapterapi;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDataReader extends GroupCloseable {
	
	//method
	ITime getSchemaTimestamp();
	
	//method declaration
	IContainer<ILoadedRecordDTO> loadAllRecordsFromTable(String tableName);
	
	//method declaration
	IContainer<String> loadAllMultiReferenceEntriesForRecord(
		String tableName,
		String entityId,
		String multiReferenceColumnName
	);
	
	//method declaration
	IContainer<Object> loadAllMultiValueEntriesFromRecord(
		String tableName,
		String entityId,
		String multiValueColumnName
	);
	
	//method declaration
	ILoadedRecordDTO loadRecordFromTableById(String tableName, String id);
	
	//method declaration
	boolean tableContainsEntityWithGivenValueAtGivenColumn(String tableName, String columnName, String value);
}
