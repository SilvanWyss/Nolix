//package declaration
package ch.nolix.system.noderawobjectdata.dataadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.noderawobjectdata.datareader.DataReader;
import ch.nolix.system.noderawobjectdata.datareader.TableDefinitionLoader;
import ch.nolix.system.noderawobjectdata.datawriter.DataWriter;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataAdapter implements IDataAdapter {
	
	//static attribute
	private static final TableDefinitionLoader tableDefinitionLoader = new TableDefinitionLoader();
	
	//attributes
	private final DataReader dataReader;
	private final DataWriter dataWriter;
	
	//constructor
	public DataAdapter(final BaseNode databaseNode) {
		
		final var tableDefinitions = tableDefinitionLoader.loadTableDefinitionsFromDatabaseNode(databaseNode);
		
		dataReader = new DataReader(databaseNode, tableDefinitions);
		dataWriter = new DataWriter(databaseNode, tableDefinitions);
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn
	) {
		dataWriter.deleteEntriesFromMultiField(tableName, recordId, multiFieldColumn);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.deleteEntryFromMultiField(tableName, recordId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public void insertEntryIntoMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.insertEntryIntoMultiField(tableName, recordId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(String tableName) {
		return dataReader.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(String tableName, String id) {
		return dataReader.loadRecordFromTableById(tableName, id);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion) {
		dataWriter.deleteRecordFromTable(tableName, recordDeletion);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertRecordIntoTable(String tableName, IRecordDTO record) {
		dataWriter.insertRecordIntoTable(tableName, record);
	}
	
	//method
	@Override
	public LinkedList<Object> loadMultiFieldEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiFieldColumnName
	) {
		return dataReader.loadMultiFieldEntriesFromRecord(tableName, recordId, multiFieldColumnName);
	}
	
	//method
	@Override
	public void saveChanges() {
		dataWriter.saveChanges();
	}
	
	//method
	@Override
	public boolean tableContainsRecordWithGivenValueAtColumn(String tableName, String columnName, String value) {
		return dataReader.tableContainsRecordWithGivenValueAtColumn(tableName, columnName, value);
	}
	
	//method
	@Override
	public void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate) {
		dataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
}
