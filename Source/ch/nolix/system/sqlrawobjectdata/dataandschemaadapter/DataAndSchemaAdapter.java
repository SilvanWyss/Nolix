//package declaration
package ch.nolix.system.sqlrawobjectdata.dataandschemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectdata.datareader.DataReader;
import ch.nolix.system.sqlrawobjectdata.datawriter.DataWriter;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IQueryCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IStatementCreator;
import ch.nolix.system.sqlrawobjectschema.schemareader.SchemaReader;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.techapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public abstract class DataAndSchemaAdapter implements IDataAndSchemaAdapter {
	
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
		final IQueryCreator queryCreator,
		final IStatementCreator statementCreator,
		final ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter pSQLSchemaAdapter
	) {
		dataReader = new DataReader(pSQLConnection, schemaAdapter, queryCreator);
		dataWriter = new DataWriter(pSQLConnection, statementCreator);
		schemaReader = new SchemaReader(pSQLConnection, pSQLSchemaAdapter);
	}
	
	//method
	@Override
	public final boolean columnIsEmpty(final String tableName, final String columnHeader) {
		return schemaReader.columnIsEmpty(tableName, columnHeader);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn
	) {
		dataWriter.deleteEntriesFromMultiField(tableName, recordId, multiFieldColumn);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.deleteEntryFromMultiField(tableName, recordId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void deleteRecordFromTable(final String tableName, final IRecordDeletionDTO recordDeletion) {
		dataWriter.deleteRecordFromTable(tableName, recordDeletion);
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.insertEntryIntoMultiField(tableName, recordId, multiFieldColumn, entry);
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
	public final LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return schemaReader.loadColumns(tableName);
	}
	
	//method
	@Override
	public final LinkedList<IFlatTableDTO> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public final LinkedList<Object> loadMultiFieldEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiFieldColumnName
	) {
		return dataReader.loadMultiFieldEntriesFromRecord(tableName, recordId, multiFieldColumnName);
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
	public final ITableDTO loadTable(final String tableName) {
		return schemaReader.loadTable(tableName);
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
		final String columnHeader,
		final String value
	) {
		return dataReader.tableContainsRecordWithGivenValueAtColumn(tableName, columnHeader, value);
	}
	
	//method
	@Override
	public final void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		dataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
}
