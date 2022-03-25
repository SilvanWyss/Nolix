//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class DataReader implements IDataReader {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final InternalDataReader internalDataReader;
	
	//TODO: Use ITableInfo instead of TableInfo.
	//multi-attribute
	private final IContainer<TableInfo> tableInfos;
	
	//constructor
	public DataReader(final BaseNode databaseNode, final IContainer<TableInfo> tableInfos) {
		
		Validator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		Validator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
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
	public LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return
		internalDataReader.loadAllMultiReferenceEntriesForRecord(
			getTableInfoByTableName(tableName),
			entityId,
			multiReferenceColumnName
		);
	}
	
	//method
	@Override
	public LinkedList<Object> loadAllMultiValueEntriesFromRecord(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		return
		internalDataReader.loadMultiValueEntriesFromRecord(
			getTableInfoByTableName(tableName),
			entityId,
			multiValueColumnName
		);
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDataReader.loadAllRecordsFromTable(getTableInfoByTableName(tableName));
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableInfoByTableName(tableName), id);
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return
		internalDataReader.tableContainsRecordWithGivenValueAtColumn(
			getTableInfoByTableName(tableName),
			columnName,
			value
		);
	}
	
	//method
	private TableInfo getTableInfoByTableName(final String tableName) {
		return tableInfos.getRefFirst(td -> td.getTableName().equals(tableName));
	}
}
