//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordQueryCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DataReader implements IDataReader {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
	
	//attribute
	private final InternalDataReader internalDataReader;
	
	//multi-attribute
	private final IContainer<ITableDefinition> tableDefinitions;
	
	//constructor
	public DataReader(
		final SQLConnection pSQLConnection,
		final ISchemaAdapter schemaAdapter,
		final IRecordQueryCreator recordQueryCreator
	) {
		
		internalDataReader = new InternalDataReader(pSQLConnection, recordQueryCreator);
		
		tableDefinitions = databaseInspector.createTableDefinitionsFrom(schemaAdapter);
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDataReader.loadAllRecordsFromTable(getTableDefinitionForTableWithName(tableName));
	}
	
	//method
	@Override
	public LinkedList<Object> loadMultiValueEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableDefinitionForTableWithName(tableName), id);
	}
	
	//method
	@Override
	public boolean tableContainsRecordWithGivenValueAtColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return
		internalDataReader.tableContainsRecordWithGivenValueAtColumn(
			tableName,
			columnName,
			value
		);
	}
	
	//method
	private ITableDefinition getTableDefinitionForTableWithName(final String tableName) {
		return tableDefinitions.getRefFirstOrNull(td -> td.getTableName().equals(tableName));
	}
}
