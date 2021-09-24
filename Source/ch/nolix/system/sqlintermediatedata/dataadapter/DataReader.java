//package declaration
package ch.nolix.system.sqlintermediatedata.dataadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.techapi.intermediatedataapi.dataadapterapi.IDataReader;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.sqldatalanguageapi.IDataQueryCreator;

//class
final class DataReader implements IDataReader {
	
	//static attribute
	private static final RecordDTOMapper recordDTOMapper = new RecordDTOMapper();
	
	//attribute
	private final SQLConnection mSQLConnection;
	private final IDataQueryCreator dataQueryCreator;
	
	//constructor
	public DataReader(final SQLConnection pSQLConnection, final IDataQueryCreator dataQueryCreator) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(dataQueryCreator).thatIsNamed(IDataQueryCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.dataQueryCreator = dataQueryCreator;
	}
	
	//method
	@Override
	public LinkedList<IRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return
		mSQLConnection
		.getRecords(dataQueryCreator.createQueryToLoadAllRecordsFromTable(tableName))
		.to(recordDTOMapper::createRecordDTOFromSQLRecord);
	}
}
