//package declaration
package ch.nolix.system.sqlrawdata.datawriter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class DataWriter implements IDataWriter {
	
	//static attribute
	public static DataWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSQLSyntaxProvider(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		return new DataWriter(databaseName, pSQLConnectionPool.borrowSQLConnection(), tableInfos, pSQLSyntaxProvider);
	}
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final InternalDataWriter internalDataWriter;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	private DataWriter(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		Validator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDataWriter = new InternalDataWriter(databaseName, pSQLConnection, pSQLSyntaxProvider);		
		this.tableInfos = tableInfos;
		
		createCloseDependencyTo(pSQLConnection);
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
		final String entityId,
		final String multiValueColumnName
	) {
		internalDataWriter.deleteEntriesFromMultiValue(
			entityId,
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
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.deleteEntryFromMultiValue(
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
			entry
		);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		internalDataWriter.deleteRecordFromTable(tableName, entity);
	}
	
	//method
	@Override
	public void expectGivenSchemaTimestamp(final Time schemaTimestamp) {
		internalDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public int getSaveCount() {
		return internalDataWriter.getSaveCount();
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
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.insertEntryIntoMultiValue(
			entityId,
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
	public void noteClose() {}
	
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
	public void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		internalDataWriter.setEntityAsUpdated(tableName, entity);
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
