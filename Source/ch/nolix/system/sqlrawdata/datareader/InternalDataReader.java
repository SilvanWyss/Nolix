//package declaration
package ch.nolix.system.sqlrawdata.datareader;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IRecordQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
final class InternalDataReader {
	
	//static attribute
	private static final LoadedRecordDTOMapper loadedRecordDTOMapper = new LoadedRecordDTOMapper();
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final IRecordQueryCreator recordQueryCreator;
	
	//attribute
	private final IMultiValueQueryCreator multiValueQueryCreator;
	
	//attribute
	private final IMultiReferenceQueryCreator multiReferenceQueryCreator;
	
	//constructor
	public InternalDataReader(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		recordQueryCreator = pSQLSyntaxProvider.getRecordQueryCreator();
		multiValueQueryCreator = pSQLSyntaxProvider.getMultiValueQueryCreator();
		multiReferenceQueryCreator = pSQLSyntaxProvider.getMultiReferenceQueryCreator();
		
		mSQLConnection.execute("USE " + databaseName);
	}
	
	//method
	public String getSchemaTimestamp() {
		return
		mSQLConnection.getOneRecord(recordQueryCreator.createQueryToLoadSchemaTimestamp()).get(0);
	}
	
	//method
	public LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo
	) {
		return
		mSQLConnection
		.getRecords(
			multiReferenceQueryCreator.createQueryToLoadAllMultiReferenceEntriesForRecord(
				entityId,
				multiReferenceColumnInfo.getColumnId()
			)
		)
		.to(r -> r.get(0));
	}
	
	//method
	public LinkedList<Object> loadMultiValueEntriesFromRecord(
		final String entityId,
		final IColumnInfo multiValueColumnInfo
	) {
		return
		mSQLConnection
		.getRecords(
			multiValueQueryCreator.createQueryToLoadMultiValueEntriesFromRecord(
				entityId,
				multiValueColumnInfo.getColumnId()
			)
		)
		.to(r -> valueMapper.createValueFromString(r.get(0), multiValueColumnInfo));
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
