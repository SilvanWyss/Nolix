//package declaration
package ch.nolix.system.sqlrawobjectdata.datawriter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

//class
public final class DataWriter implements IDataWriter {
	
	//attribute
	private final InternalDataWriter internalDataWriter;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	public DataWriter(
		final SQLConnection pSQLConnection,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		Validator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDataWriter = new InternalDataWriter(pSQLConnection, pSQLSyntaxProvider);		
		this.tableInfos = tableInfos;
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		internalDataWriter.deleteEntriesFromMultiReference(
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).getColumnId()
		);
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		internalDataWriter.deleteEntriesFromMultiValue(
			recordId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId()
		);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		internalDataWriter.deleteEntryFromMultiReference(
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiRefereceColumnName).getColumnId(),
			referencedEntityId
		);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.deleteEntryFromMultiValue(
			recordId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
			entry
		);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordHeadDTO recordHead) {
		internalDataWriter.deleteRecordFromTable(tableName, recordHead);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return internalDataWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		internalDataWriter.insertEntryIntoMultiReference(
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).getColumnId(),
			referencedEntityId
		);
	}
	
	//method
	@Override
	public void insertEntryIntoMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.insertEntryIntoMultiValue(
			recordId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
			entry
		);
	}
	
	//method
	@Override
	public void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		internalDataWriter.insertRecordIntoTable(tableName, record);
	}
	
	//method
	@Override
	public void reset() {
		internalDataWriter.reset();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		internalDataWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		internalDataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
	
	//method
	private IColumnInfo getColumnDefinitionByTableNameAndColumnName(
		final String tableName,
		final String columnName
	) {
		return getTableDefinitionByTableName(tableName).getColumnInfoByColumnName(columnName);
	}
	
	//method
	private ITableInfo getTableDefinitionByTableName(final String tableName) {
		return tableInfos.getRefFirstOrNull(td -> td.getTableName().equals(tableName));
	}
}
