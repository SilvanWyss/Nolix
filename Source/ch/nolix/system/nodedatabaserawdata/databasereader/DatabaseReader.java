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
	public IContainer<String> loadMultiReferenceEntries(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		
		final var tableInfo = getTableInfoByTableName(tableName);
		
		return
		internalDatabaseReader.loadMultiReferenceEntries(
			tableInfo,
			entityId,
			tableInfo.getColumnInfoByColumnName(multiReferenceColumnName)
		);
	}
	
	//method
	@Override
	public IContainer<Object> loadMultiValueEntries(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		
		final var tableInfo = getTableInfoByTableName(tableName);
		
		return
		internalDatabaseReader.loadMultiValueEntries(
			tableInfo,
			entityId,
			tableInfo.getColumnInfoByColumnName(multiValueColumnName)
		);
	}
	
	//method
	@Override
	public IContainer<ILoadedEntityDTO> loadEntitiesOfTable(final String tableName) {
		return internalDatabaseReader.loadEntitiesOfTable(getTableInfoByTableName(tableName));
	}
	
	//method
	@Override
	public ILoadedEntityDTO loadEntity(final String tableName, final String id) {
		return internalDatabaseReader.loadEntity(getTableInfoByTableName(tableName), id);
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
	@Override
	public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
		return internalDatabaseReader.tableContainsEntityWithGivenId(tableName, id);
	}
	
	//method
	private ITableInfo getTableInfoByTableName(final String tableName) {
		return tableInfos.getRefFirst(td -> td.getTableName().equals(tableName));
	}
}
