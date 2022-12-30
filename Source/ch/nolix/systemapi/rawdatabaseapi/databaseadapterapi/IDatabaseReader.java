//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDatabaseReader extends GroupCloseable {
	
	//method
	ITime getSchemaTimestamp();
	
	//method declaration
	IContainer<ILoadedEntityDTO> loadAllRecordsFromTable(String tableName);
	
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
	ILoadedEntityDTO loadRecordFromTableById(String tableName, String id);
	
	//method declaration
	boolean tableContainsEntityWithGivenValueAtGivenColumn(String tableName, String columnName, String value);
}
