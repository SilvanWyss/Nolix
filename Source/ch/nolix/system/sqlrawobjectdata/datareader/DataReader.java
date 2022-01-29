//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordQueryCreator;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

//class
public final class DataReader implements IDataReader {
	
	//attribute
	private final InternalDataReader internalDataReader;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	public DataReader(
		final SQLConnection pSQLConnection,
		final IContainer<ITableInfo> tableInfos,
		final IRecordQueryCreator recordQueryCreator,
		final IMultiValueQueryCreator multiValueQueryCreator
	) {
		
		Validator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDataReader = new InternalDataReader(pSQLConnection, recordQueryCreator, multiValueQueryCreator);
		
		this.tableInfos = tableInfos;
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDataReader.loadAllRecordsFromTable(getTableDefinitionByTableName(tableName));
	}
	
	//method
	@Override
	public LinkedList<Object> loadMultiValueEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		return
		internalDataReader.loadMultiValueEntriesFromRecord(
			recordId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName)
		);
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableDefinitionByTableName(tableName), id);
	}
	
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
