//package declaration
package ch.nolix.system.sqlrawobjectdata.dataadapter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawobjectdata.datareader.DataReader;
import ch.nolix.system.sqlrawobjectdata.datawriter.DataWriter;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class DataAdapter implements IDataAdapter {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
	
	//attributes
	private final DataReader dataReader;
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
	public final void deleteRecordFromTable(final String tableName, final IRecordHeadDTO recordHead) {
		dataWriter.deleteRecordFromTable(tableName, recordHead);
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return dataWriter.hasChanges();
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
	public final void saveChanges() {
		dataWriter.saveChanges();
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
