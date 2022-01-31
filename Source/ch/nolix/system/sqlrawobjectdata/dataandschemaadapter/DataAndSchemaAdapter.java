//package declaration
package ch.nolix.system.sqlrawobjectdata.dataandschemaadapter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawobjectdata.datareader.DataReader;
import ch.nolix.system.sqlrawobjectdata.datawriter.DataWriter;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordQueryCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordStatementCreator;
import ch.nolix.system.sqlrawobjectschema.schemareader.SchemaReader;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public abstract class DataAndSchemaAdapter implements IDataAndSchemaAdapter {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
	
	//attribute
	private final IDataReader dataReader;
	
	//attribute
	private final IDataWriter dataWriter;
	
	//attribute
	private final SchemaReader schemaReader;
	
	//constructor
	public DataAndSchemaAdapter(
		final SQLConnection pSQLConnection,
		final ISchemaAdapter schemaAdapter,
		final IRecordQueryCreator recordQueryCreator,
		final IRecordStatementCreator recordStatementCreator,
		final IMultiValueQueryCreator multiValueQueryCreator,
		final IMultiValueStatementCreator multiValueStatementCreator,
		final ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter pSQLSchemaAdapter
	) {
		
		final var tableDefinitions = databaseInspector.createTableDefinitionsFrom(schemaAdapter);
		
		dataReader = new DataReader(pSQLConnection, tableDefinitions, recordQueryCreator, multiValueQueryCreator);
		dataWriter = new DataWriter(pSQLConnection, tableDefinitions, recordStatementCreator, multiValueStatementCreator);
		schemaReader = new SchemaReader(pSQLConnection, pSQLSchemaAdapter);
	}
	
	//method
	@Override
	public final boolean columnIsEmpty(final String tableName, final String columnName) {
		return schemaReader.columnIsEmpty(tableName, columnName);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		dataWriter.deleteEntriesFromMultiValue(tableName, recordId, multiValueColumnName);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		dataWriter.deleteEntryFromMultiValue(tableName, recordId, multiValueColumnName, entry);
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
		final String multiValueColumnName,
		final String entry
	) {
		dataWriter.insertEntryIntoMultiValue(tableName, recordId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public final void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		dataWriter.insertRecordIntoTable(tableName, record);
	}
	
	//method	
	@Override
	public final LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return dataReader.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public final LinkedList<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return schemaReader.loadColumnsByTableId(tableId);
	}
	
	//method
	@Override
	public final LinkedList<IColumnDTO> loadColumnsByTableName(final String tableName) {
		return schemaReader.loadColumnsByTableName(tableName);
	}
	
	//method
	@Override
	public final ITableDTO loadTableById(final String id) {
		return schemaReader.loadTableById(id);
	}
	
	//method
	@Override
	public final IFlatTableDTO loadFlatTableByName(final String name) {
		return schemaReader.loadFlatTableByName(name);
	}
	
	//method
	@Override
	public final LinkedList<IFlatTableDTO> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public final LinkedList<Object> loadMultiValueEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiFieldColumnName
	) {
		return dataReader.loadMultiValueEntriesFromRecord(tableName, recordId, multiFieldColumnName);
	}
	
	//method
	@Override
	public final ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return dataReader.loadRecordFromTableById(tableName, id);
	}
	
	//method
	@Override
	public final Time loadSchemaTimestamp() {
		return schemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableById(String id) {
		return schemaReader.loadFlatTableById(id);
	}
	
	//method
	@Override
	public final ITableDTO loadTableByName(final String name) {
		return schemaReader.loadTableByName(name);
	}
	
	//method
	@Override
	public final LinkedList<ITableDTO> loadTables() {
		return schemaReader.loadTables();
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
