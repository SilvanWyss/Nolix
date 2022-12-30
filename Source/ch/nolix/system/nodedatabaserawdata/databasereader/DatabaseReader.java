//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseReader;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class DatabaseReader implements IDatabaseReader {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final InternalDatabaseReader internalDatabaseReader;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	public DatabaseReader(final IMutableNode<?> databaseNode, final IContainer<ITableInfo> tableInfos) {
		
		GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDatabaseReader = new InternalDatabaseReader(databaseNode);
		this.tableInfos = tableInfos;
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public Time getSchemaTimestamp() {
		return internalDatabaseReader.getSchemaTimestamp();
	}
	
	//method
	@Override
	public IContainer<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		
		final var tableInfo = getTableInfoByTableName(tableName);
		
		return
		internalDatabaseReader.loadAllMultiReferenceEntriesForRecord(
			tableInfo,
			entityId,
			tableInfo.getColumnInfoByColumnName(multiReferenceColumnName)
		);
	}
	
	//method
	@Override
	public IContainer<Object> loadAllMultiValueEntriesFromRecord(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		
		final var tableInfo = getTableInfoByTableName(tableName);
		
		return
		internalDatabaseReader.loadMultiValueEntriesFromRecord(
			tableInfo,
			entityId,
			tableInfo.getColumnInfoByColumnName(multiValueColumnName)
		);
	}
	
	//method
	@Override
	public IContainer<ILoadedEntityDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDatabaseReader.loadAllRecordsFromTable(getTableInfoByTableName(tableName));
	}
	
	//method
	@Override
	public ILoadedEntityDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDatabaseReader.loadRecordFromTableById(getTableInfoByTableName(tableName), id);
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		
		final var tableInfo = getTableInfoByTableName(tableName);
		
		return
		internalDatabaseReader.tableContainsEntityWithGivenValueAtGivenColumn(
			getTableInfoByTableName(tableName),
			tableInfo.getColumnInfoByColumnName(columnName),
			value
		);
	}
	
	//method
	private ITableInfo getTableInfoByTableName(final String tableName) {
		return tableInfos.getRefFirst(td -> td.getTableName().equals(tableName));
	}
}
