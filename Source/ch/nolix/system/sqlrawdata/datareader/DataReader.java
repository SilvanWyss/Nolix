//package declaration
package ch.nolix.system.sqlrawdata.datareader;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class DataReader implements IDataReader {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final InternalDataReader internalDataReader;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	public DataReader(
		final SQLConnection pSQLConnection,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		Validator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDataReader = new InternalDataReader(pSQLConnection, pSQLSyntaxProvider);
		this.tableInfos = tableInfos;
		
		createCloseDependencyTo(pSQLConnection);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return internalDataReader.loadAllMultiReferenceEntriesForRecord(
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName)
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
			entityId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName)
		);
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDataReader.loadAllRecordsFromTable(getTableDefinitionByTableName(tableName));
	}

	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableDefinitionByTableName(tableName), id);
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public boolean tableContainsRecordWithGivenValueAtColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return
		internalDataReader.tableContainsRecordWithGivenValueAtColumn(
			tableName,
			columnName,
			value
		);
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
