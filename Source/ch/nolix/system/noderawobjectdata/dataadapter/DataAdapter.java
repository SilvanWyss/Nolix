//package declaration
package ch.nolix.system.noderawobjectdata.dataadapter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.noderawobjectdata.datareader.DataReader;
import ch.nolix.system.noderawobjectdata.datareader.TableDefinitionLoader;
import ch.nolix.system.noderawobjectdata.datawriter.DataWriter;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataAdapter implements IDataAdapter {
	
	//static attribute
	private static final TableDefinitionLoader tableDefinitionLoader = new TableDefinitionLoader();
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final DataReader dataReader;
	
	//attribute
	private final DataWriter dataWriter;
	
	//constructor
	public DataAdapter(final BaseNode databaseNode) {
		
		final var tableDefinitions = tableDefinitionLoader.loadTableDefinitionsFromDatabaseNode(databaseNode);
		
		dataReader = new DataReader(databaseNode, tableDefinitions);
		dataWriter = new DataWriter(databaseNode, tableDefinitions);
		
		createCloseDependencyTo(dataReader);
		createCloseDependencyTo(dataWriter);
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		dataWriter.deleteEntriesFromMultiReference(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		dataWriter.deleteEntriesFromMultiValue(tableName, recordId, multiValueColumnName);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		dataWriter.deleteEntryFromMultiReference(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		dataWriter.deleteEntryFromMultiValue(tableName, recordId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(String tableName, IEntityHeadDTO recordHead) {
		dataWriter.deleteRecordFromTable(tableName, recordHead);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public int getSaveCount() {
		return dataWriter.getSaveCount();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		dataWriter.insertEntryIntoMultiReference(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public void insertEntryIntoMultiValue(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.insertEntryIntoMultiValue(tableName, recordId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public void insertRecordIntoTable(String tableName, IRecordDTO record) {
		dataWriter.insertRecordIntoTable(tableName, record);
	}
	
	//method
	@Override
	public LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String recordId,
		final String multiReferenceColumnName
	) {
		return dataReader.loadAllMultiReferenceEntriesForRecord(tableName, recordId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public LinkedList<Object> loadAllMultiValueEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiFieldColumnName
	) {
		return dataReader.loadAllMultiValueEntriesFromRecord(tableName, recordId, multiFieldColumnName);
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
	public void noteClose() {}
	
	//method
	@Override
	public void reset() {
		dataWriter.reset();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		dataWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		dataWriter.setEntityAsUpdated(tableName, entity);
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
