//package declaration
package ch.nolix.systemapi.rawobjectdataapi.dataadapterapi;

//own imports
import ch.nolix.core.skillapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//interface
/**
 * @author Silvan Wyss
 * @date 2021-09-19
 */
public interface IDataWriter extends IMultiTimeChangeSaver {
	
	//method declaration
	/**
	 * There is not asserted that the concerned record was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordId
	 * @param multiValueColumnName
	 */
	void deleteEntriesFromMultiValue(String tableName, String recordId, String multiValueColumnName);
	
	//method declaration
	/**
	 * There is not asserted that the concerned record was not changed in the meanwhile.
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
	 * Asserts that the concerned record was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordHead
	 */
	void deleteRecordFromTable(String tableName, IRecordHeadDTO recordHead);
	
	//method declaration
	/**
	 * There is not asserted that the concerned record was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordId
	 * @param multiValueColumnName
	 * @param entry
	 */
	void insertEntryIntoMultiValue(String tableName, String recordId, String multiValueColumnName, String entry);
	
	//method declaration
	/**
	 * Asserts that the concerned record was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param record
	 */
	void insertRecordIntoTable(String tableName, IRecordDTO record);
	
	//method declaration
	/**
	 *  Asserts that the concerned record was not changed in the meanwhile.
	 * 
	 * @param tableName
	 * @param recordUpdate
	 */
	void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate);
}
