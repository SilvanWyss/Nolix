//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlintermediatedata.sqlapi.IQueryCreator;
import ch.nolix.system.sqlintermediatedata.sqlapi.ITableDefinitionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ILoadedRecordDTO;

//class
final class InternalDataReader {
	
	//static attribute
	private static final LoadedRecordDTOMapper loadedRecordDTOMapper = new LoadedRecordDTOMapper();
	
	//attribute
	private final SQLConnection mSQLConnection;
	private final IQueryCreator queryCreator;
	
	//constructor
	public InternalDataReader(final SQLConnection pSQLConnection, final IQueryCreator queryCreator) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(queryCreator).thatIsNamed(IQueryCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.queryCreator = queryCreator;
	}
	
	//method
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final ITableDefinitionDTO tableDefinition) {
		return
		mSQLConnection
		.getRecords(queryCreator.createQueryToLoadAllRecordsFromTable(tableDefinition))
		.to(r -> loadedRecordDTOMapper.createLoadedRecordDTOFromSQLRecord(r, tableDefinition));
	}
	
	//method
	public ILoadedRecordDTO loadRecordFromTableById(final ITableDefinitionDTO tableDefinition, final String id) {
		return
		loadedRecordDTOMapper.createLoadedRecordDTOFromSQLRecord(
			mSQLConnection.getOneRecord(queryCreator.createQueryToLoadRecordFromTableById(id, tableDefinition)),
			tableDefinition
		);
	}
}
