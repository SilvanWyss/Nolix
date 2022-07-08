//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class DataReader implements IDataReader {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final InternalDataReader internalDataReader;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	public DataReader(final IMutableNode<?> databaseNode, final IContainer<ITableInfo> tableInfos) {
		
		GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDataReader = new InternalDataReader(databaseNode);
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
		return internalDataReader.getSchemaTimestamp();
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
		internalDataReader.loadAllMultiReferenceEntriesForRecord(
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
		internalDataReader.loadMultiValueEntriesFromRecord(
			tableInfo,
			entityId,
			tableInfo.getColumnInfoByColumnName(multiValueColumnName)
		);
	}
	
	//method
	@Override
	public IContainer<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDataReader.loadAllRecordsFromTable(getTableInfoByTableName(tableName));
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableInfoByTableName(tableName), id);
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
		internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(
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
