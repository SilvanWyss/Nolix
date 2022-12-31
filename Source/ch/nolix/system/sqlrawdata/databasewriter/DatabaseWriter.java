//package declaration
package ch.nolix.system.sqlrawdata.databasewriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseWriter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class DatabaseWriter implements IDatabaseWriter {
	
	//static attribute
	public static DatabaseWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSQLSyntaxProvider(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		return new DatabaseWriter(databaseName, pSQLConnectionPool.borrowSQLConnection(), tableInfos, pSQLSyntaxProvider);
	}
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final InternalDatabaseWriter internalDatabaseWriter;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	private DatabaseWriter(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDatabaseWriter = new InternalDatabaseWriter(databaseName, pSQLConnection, pSQLSyntaxProvider);		
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
		internalDatabaseWriter.deleteEntriesFromMultiReference(
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
		internalDatabaseWriter.deleteEntriesFromMultiValue(
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
		internalDatabaseWriter.deleteEntryFromMultiReference(
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
		internalDatabaseWriter.deleteEntryFromMultiValue(
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
			entry
		);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		internalDatabaseWriter.deleteRecordFromTable(tableName, entity);
	}
	
	//method
	@Override
	public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		internalDatabaseWriter.expectGivenSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void expectTableContainsEntity(final String tableName, final String entityId) {
		internalDatabaseWriter.expectTableContainsEntity(tableName, entityId);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public int getSaveCount() {
		return internalDatabaseWriter.getSaveCount();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return internalDatabaseWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		internalDatabaseWriter.insertEntryIntoMultiReference(
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
		internalDatabaseWriter.insertEntryIntoMultiValue(
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
			entry
		);
	}
	
	//method
	@Override
	public void insertNewEntityIntoTable(final String tableName, final INewEntityDTO newEntity) {
		internalDatabaseWriter.insertRecordIntoTable(tableName, newEntity);
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public void reset() {
		internalDatabaseWriter.reset();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		internalDatabaseWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		internalDatabaseWriter.setEntityAsUpdated(tableName, entity);
	}
	
	//method
	@Override
	public void updateEntityOnTable(final String tableName, final IEntityUpdateDTO recordUpdate) {
		internalDatabaseWriter.updateEntityOnTable(tableName, recordUpdate);
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
