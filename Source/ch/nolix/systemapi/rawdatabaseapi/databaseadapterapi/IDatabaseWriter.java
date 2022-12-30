//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
/**
 * @author Silvan Wyss
 * @date 2021-09-19
 */
public interface IDatabaseWriter extends IMultiTimeChangeSaver {
	
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
	 * Will cause an error if the concerned table does not contain an entity with the given entityId.
	 * This method can be used to prevent from referencing an entity that was deleted in the meanwhile.
	 * 
	 * @param tableName
	 * @param id
	 */
	void expectTableContainsEntity(String tableName, String entityId);
	
	//method declaration
	/**
	 * Will cause an error if the database does not have the given schema timestamp.
	 * 
	 * @param schemaTimestamp
	 */
	void expectGivenSchemaTimestamp(final ITime schemaTimestamp);
	
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
	 * Inserts the given pRecord into the table with the given tableName.
	 * 
	 * @param tableName
	 * @param pRecord
	 */
	void insertRecordIntoTable(String tableName, INewEntityDTO pRecord);
	
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
	void updateEntityOnTable(String tableName, IEntityUpdateDTO recordUpdate);
}
