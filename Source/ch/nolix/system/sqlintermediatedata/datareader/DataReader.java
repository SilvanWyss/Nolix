//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlintermediatedata.sqlapi.IQueryCreator;
import ch.nolix.techapi.intermediatedataapi.dataadapterapi.IDataReader;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ILoadedRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ITableDefinitionDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DataReader implements IDataReader {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
	
	//attribute
	private final InternalDataReader internalDataReader;
	
	//multi-attribute
	private final IContainer<ITableDefinitionDTO> tableDefinitions;
	
	//constructor
	public DataReader(
		final SQLConnection pSQLConnection,
		final ISchemaAdapter schemaAdapter,
		final IQueryCreator queryCreator
	) {
		
		internalDataReader = new InternalDataReader(pSQLConnection, queryCreator);
		
		tableDefinitions = databaseInspector.createTableDefinitionsFrom(schemaAdapter);
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDataReader.loadAllRecordsFromTable(getTableDefinitionForTableWithName(tableName));
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableDefinitionForTableWithName(tableName), id);
	}
	
	//method
	private ITableDefinitionDTO getTableDefinitionForTableWithName(final String tableName) {
		return tableDefinitions.getRefFirst(td -> td.getName().equals(tableName));
	}
}
