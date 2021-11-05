//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IQueryCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.ILoadedRecordDTO;

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
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final ITableDefinition tableDefinition) {
		return
		mSQLConnection
		.getRecords(queryCreator.createQueryToLoadAllRecordsFromTable(tableDefinition))
		.to(r -> loadedRecordDTOMapper.createLoadedRecordDTOFromSQLRecord(r, tableDefinition));
	}
	
	//method
	public ILoadedRecordDTO loadRecordFromTableById(final ITableDefinition tableDefinition, final String id) {
		return
		loadedRecordDTOMapper.createLoadedRecordDTOFromSQLRecord(
			mSQLConnection.getOneRecord(queryCreator.createQueryToLoadRecordFromTableById(id, tableDefinition)),
			tableDefinition
		);
	}
	
	//method
	public boolean tableContainsRecordWithGivenValueAtColumn(
		final String tableName,
		final String columnHeader,
		final String value
	) {
		return
		Integer.valueOf(
			mSQLConnection.getOneRecord(
				queryCreator.createQueryToCountRecordsWithGivenValueAtGivenColumn(tableName, columnHeader, value)
			)
			.get(0)
		)
		> 0;
	}
}
