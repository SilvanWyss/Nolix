//package declaration
package ch.nolix.systemapi.rawdataapi.dataadapterapi;

import ch.nolix.core.skilluniversalapi.IMultiTimeChangeSaver;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

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
	 * @param entityId
	 * @param multiValueColumnName
	 */
	void deleteEntriesFromMultiValue(String tableName, String entityId, String multiValueColumnName);
	
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
	 * @param entityId
	 * @param multiValueColumnName
	 * @param entry
	 */
	void deleteEntryFromMultiValue(
		String tableName,
		String entityId,
		String multiValueColumnName,
		String entry
	);
	
	//method declaration
	/**
	 * Causes an error if the concerned entity was deleted or changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param entity
	 */
	void deleteRecordFromTable(String tableName, IEntityHeadDTO entity);
	
	//method declaration
	/**
	 * Will cause an error if the database does not have the given schema timestamp.
	 * 
	 * @param schemaTimestamp
	 */
	void expectGivenSchemaTimestamp(final Time schemaTimestamp);
	
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
	 * @param entityId
	 * @param multiValueColumnName
	 * @param entry
	 */
	void insertEntryIntoMultiValue(String tableName, String entityId, String multiValueColumnName, String entry);
	
	//method declaration
	/**
	 * @param tableName
	 * @param record
	 */
	void insertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	/**
	 * Sets the concerned entity as updated.
	 * Will cause an error if the concerned entity was deleted or changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param entity
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
