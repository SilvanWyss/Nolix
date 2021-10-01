//package declaration
package ch.nolix.system.sqloptimisticlockingdata.dataadapter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlschema.schemaadapter.MSSQLSchemaAdapter;
import ch.nolix.techapi.sqloptimisticlockingdataapi.dataadapterapi.IDataReader;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.sqloptimisticlockingdataapi.sqldatalanguageapi.IDataQueryCreator;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
final class DataReader implements IDataReader {
	
	//static attribute
	private static final RecordDTOMapper recordDTOMapper = new RecordDTOMapper();
	
	//attributes
	private final SQLConnection mSQLConnection;
	private final IContainer<ITableDTO> tableDefinitions;
	private final IDataQueryCreator dataQueryCreator;
	
	//constructor
	public DataReader(final SQLConnection pSQLConnection, final IDataQueryCreator dataQueryCreator) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(dataQueryCreator).thatIsNamed(IDataQueryCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		tableDefinitions = new MSSQLSchemaAdapter(mSQLConnection).loadTables();
		this.dataQueryCreator = dataQueryCreator;
	}
	
	//method
	@Override
	public LinkedList<IRecordDTO> loadAllRecordsFromTable(final String tableName) {
		
		final var table = getDefinitionOfTableWithName(tableName);
		
		return
		mSQLConnection
		.getRecords(dataQueryCreator.createQueryToLoadAllRecordsFromTable(tableName))
		.to(r -> recordDTOMapper.createRecordDTOFromSQLRecord(r, table));
	}
	
	//method
	private ITableDTO getDefinitionOfTableWithName(final String tableName) {
		return tableDefinitions.getRefFirst(t -> t.getName().equals(tableName));
	}
}
