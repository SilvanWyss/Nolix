//package declaration
package ch.nolix.system.sqlrawdata.databasereader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IEntityQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
final class InternalDatabaseReader {
	
	//static attribute
	private static final LoadedEntityDTOMapper loadedEntityDTOMapper = new LoadedEntityDTOMapper();
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final IEntityQueryCreator entityQueryCreator;
	
	//attribute
	private final IMultiValueQueryCreator multiValueQueryCreator;
	
	//attribute
	private final IMultiReferenceQueryCreator multiReferenceQueryCreator;
	
	//constructor
	public InternalDatabaseReader(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		entityQueryCreator = pSQLSyntaxProvider.getEntityQueryCreator();
		multiValueQueryCreator = pSQLSyntaxProvider.getMultiValueQueryCreator();
		multiReferenceQueryCreator = pSQLSyntaxProvider.getMultiReferenceQueryCreator();
		
		mSQLConnection.execute("USE " + databaseName);
	}
	
	//method
	public Time getSchemaTimestamp() {
		return
		Time.fromString(
			mSQLConnection.getOneRecord(entityQueryCreator.createQueryToLoadSchemaTimestamp()).get(0)
		);
	}
	
	//method
	public IContainer<String> loadMultiReferenceEntries(
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
	public IContainer<Object> loadMultiValueEntries(
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
	public IContainer<ILoadedEntityDTO> loadEntitiesOfTable(final ITableInfo tableInfo) {
		return
		mSQLConnection
		.getRecords(entityQueryCreator.createQueryToLoadAllRecordsFromTable(tableInfo))
		.to(r -> loadedEntityDTOMapper.createLoadedRecordDTOFromSQLRecord(r, tableInfo));
	}
	
	//method
	public ILoadedEntityDTO loadEntity(final ITableInfo tableInfo, final String id) {
		return
		loadedEntityDTOMapper.createLoadedRecordDTOFromSQLRecord(
			mSQLConnection.getOneRecord(entityQueryCreator.createQueryToLoadRecordFromTableById(id, tableInfo)),
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
			case VALUE, OPTIONAL_VALUE, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE:
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
				throw InvalidArgumentException.forArgument(columnInfo.getColumnPropertyType());
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
				entityQueryCreator.createQueryToCountRecordsWithGivenValueAtGivenColumn(
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
