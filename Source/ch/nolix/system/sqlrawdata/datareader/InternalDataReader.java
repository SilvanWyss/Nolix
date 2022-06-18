//package declaration
package ch.nolix.system.sqlrawdata.datareader;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IRecordQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.system.time.moment.Time;
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
		
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		recordQueryCreator = pSQLSyntaxProvider.getRecordQueryCreator();
		multiValueQueryCreator = pSQLSyntaxProvider.getMultiValueQueryCreator();
		multiReferenceQueryCreator = pSQLSyntaxProvider.getMultiReferenceQueryCreator();
		
		mSQLConnection.execute("USE " + databaseName);
	}
	
	//method
	public Time getSchemaTimestamp() {
		return
		Time.fromString(
			mSQLConnection.getOneRecord(recordQueryCreator.createQueryToLoadSchemaTimestamp()).get(0)
		);
	}
	
	//method
	public IContainer<String> loadAllMultiReferenceEntriesForRecord(
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
	public IContainer<Object> loadMultiValueEntriesFromRecord(
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
	public IContainer<ILoadedRecordDTO> loadAllRecordsFromTable(final ITableInfo tableInfo) {
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
	public boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final String tableName,
		final IColumnInfo columnInfo,
		final String value
	) {
		switch (columnInfo.getColumnPropertyType()) {
			case VALUE:
			case OPTIONAL_VALUE:
			case REFERENCE:
			case OPTIONAL_REFERENCE:
			case BACK_REFERENCE:
			case OPTIONAL_BACK_REFERENCE:
				return
				tableContainsEntityWithGivenValueAtGivenSingleColumn(
					tableName,
					columnInfo.getColumnName(),
					value
				);
			case MULTI_VALUE:
				return multiValueEntryExistsForGivenColumnAndValue(columnInfo.getColumnId(), value);
			case MULTI_REFERENCE:
				return
				multiReferenceEntryExistsForGivenColumnAndReferencedEntity(columnInfo.getColumnId(), value);
			default:
				throw new InvalidArgumentException(columnInfo.getColumnPropertyType());
		}
	}
	
	//method
	private boolean multiReferenceEntryExistsForGivenColumnAndReferencedEntity(
		final String columnId,
		final String referencedEntityId
	) {
		return
		mSQLConnection.getRecords(
			multiReferenceQueryCreator
			.createQueryToLoadOneOrNoneMultiReferenceEntryForGivenColumnAndReferencedEntity(
				columnId,
				referencedEntityId
			)
		).containsAny();
	}
	
	//method
	private boolean multiValueEntryExistsForGivenColumnAndValue(
		final String columnId,
		final String value
	) {
		return
		mSQLConnection.getRecords(
			multiValueQueryCreator.createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
				columnId,
				value
			)
		).containsAny();
	}
	
	//method
	private boolean tableContainsEntityWithGivenValueAtGivenSingleColumn(
		final String tableName,
		final String singleColumnName,
		final String value
	) {
		return
		Integer.valueOf(
			mSQLConnection.getOneRecord(
				recordQueryCreator.createQueryToCountRecordsWithGivenValueAtGivenColumn(
					tableName,
					singleColumnName,
					value
				)
			)
			.get(0)
		)
		> 0;
	}
}
