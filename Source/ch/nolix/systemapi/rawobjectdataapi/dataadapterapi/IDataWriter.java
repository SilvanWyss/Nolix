//package declaration
package ch.nolix.systemapi.rawobjectdataapi.dataadapterapi;

//own imports
import ch.nolix.core.skillapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
/**
 * @author Silvan Wyss
 * @date 2021-09-19
 */
public interface IDataWriter extends IMultiTimeChangeSaver {
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param entityId
	 * @param multiReferenceColumnName
	 */
	void deleteEntriesFromMultiReference(String tableName, String entityId, String multiReferenceColumnName);
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordId
	 * @param multiValueColumnName
	 */
	void deleteEntriesFromMultiValue(String tableName, String recordId, String multiValueColumnName);
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param entityId
	 * @param multiRefereceColumnName
	 * @param referencedEntityId
	 */
	void deleteEntryFromMultiReference(
		String tableName,
		String entityId,
		String multiRefereceColumnName,
		String referencedEntityId
	);
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordId
	 * @param multiValueColumnName
	 * @param entry
	 */
	void deleteEntryFromMultiValue(
		String tableName,
		String recordId,
		String multiValueColumnName,
		String entry
	);
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordHead
	 */
	void deleteRecordFromTable(String tableName, IEntityHeadDTO recordHead);
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param entityId
	 * @param multiReferenceColumnName
	 * @param referencedEntityId
	 */
	void insertEntryIntoMultiReference(
		String tableName,
		String entityId,
		String multiReferenceColumnName,
		String referencedEntityId
	);
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordId
	 * @param multiValueColumnName
	 * @param entry
	 */
	void insertEntryIntoMultiValue(String tableName, String recordId, String multiValueColumnName, String entry);
	
	//method declaration
	/**
	 * @param tableName
	 * @param record
	 */
	void insertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	/**
	 * Sets the concerned entity as updated
	 * 
	 * @param tableName
	 * @param entity
	 * @throws Exception if the concerned entity was deleted or changed in the meanwhile.
	 */
	void setEntityAsUpdated(String tableName, IEntityHeadDTO entity);
	
	//method declaration
	/**
	 * There is not asserted that the concerned entity was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordUpdate
	 */
	void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
