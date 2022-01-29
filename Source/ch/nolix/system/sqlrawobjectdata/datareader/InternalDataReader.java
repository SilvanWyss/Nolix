//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordQueryCreator;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

//class
final class InternalDataReader {
	
	//static attribute
	private static final LoadedRecordDTOMapper loadedRecordDTOMapper = new LoadedRecordDTOMapper();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final IRecordQueryCreator recordQueryCreator;
	
	//constructor
	public InternalDataReader(final SQLConnection pSQLConnection, final IRecordQueryCreator recordQueryCreator) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(recordQueryCreator).thatIsNamed(IRecordQueryCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.recordQueryCreator = recordQueryCreator;
	}
	
	//method
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final ITableInfo tableInfo) {
		return
		mSQLConnection
		.getRecords(recordQueryCreator.createQueryToLoadAllRecordsFromTable(tableInfo))
		.to(r -> loadedRecordDTOMapper.createLoadedRecordDTOFromSQLRecord(r, tableInfo));
	}
	
	//method
	public ILoadedRecordDTO loadRecordFromTableById(final ITableInfo tableInfo, final String id) {
		return
		loadedRecordDTOMapper.createLoadedRecordDTOFromSQLRecord(
			mSQLConnection.getOneRecord(recordQueryCreator.createQueryToLoadRecordFromTableById(id, tableInfo)),
			tableInfo
		);
	}
	
	//method
	public boolean tableContainsRecordWithGivenValueAtColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return
		Integer.valueOf(
			mSQLConnection.getOneRecord(
				recordQueryCreator.createQueryToCountRecordsWithGivenValueAtGivenColumn(tableName, columnName, value)
			)
			.get(0)
		)
		> 0;
	}
}
