//package declaration
package ch.nolix.system.sqlrawobjectdata.dataadapter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawobjectdata.datareader.DataReader;
import ch.nolix.system.sqlrawobjectdata.datawriter.DataWriter;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class DataAdapter implements IDataAdapter {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final DataReader dataReader;
	
	//attribute
	private final DataWriter dataWriter;
	
	//constructor
	public DataAdapter(
		final SQLConnection pSQLConnection,
		final ISchemaAdapter schemaAdapter,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		final var tableDefinitions = databaseInspector.createTableDefinitionsFrom(schemaAdapter);
		
		dataReader = new DataReader(pSQLConnection, tableDefinitions, pSQLSyntaxProvider);
		dataWriter = new DataWriter(pSQLConnection, tableDefinitions, pSQLSyntaxProvider);
		
		createCloseDependencyTo(pSQLConnection);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		dataWriter.deleteEntriesFromMultiReference(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiFieldColumn
	) {
		dataWriter.deleteEntriesFromMultiValue(tableName, recordId, multiFieldColumn);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		dataWriter.deleteEntryFromMultiReference(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.deleteEntryFromMultiValue(tableName, recordId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void deleteRecordFromTable(final String tableName, final IEntityHeadDTO recordHead) {
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
	public final boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		dataWriter.insertEntryIntoMultiReference(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiValue(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.insertEntryIntoMultiValue(tableName, recordId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		dataWriter.insertRecordIntoTable(tableName, record);
	}
	
	@Override
	public final LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String recordId,
		final String multiReferenceColumnName
	) {
		return dataReader.loadAllMultiReferenceEntriesForRecord(tableName, recordId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final LinkedList<Object> loadAllMultiValueEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiFieldColumnName
	) {
		return dataReader.loadAllMultiValueEntriesFromRecord(tableName, recordId, multiFieldColumnName);
	}
	
	//method	
	@Override
	public final LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return dataReader.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public final ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return dataReader.loadRecordFromTableById(tableName, id);
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		dataWriter.reset();
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		dataWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public final boolean tableContainsRecordWithGivenValueAtColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return dataReader.tableContainsRecordWithGivenValueAtColumn(tableName, columnName, value);
	}
	
	//method
	@Override
	public final void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		dataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
}
