//package declaration
package ch.nolix.system.sqlrawdata.schemainfo;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class TableInfo implements ITableInfo {
	
	//attribute
	private final String tableId;
	
	//attribute
	private final String tableName;
	
	//multi-attribute
	private final IContainer<IColumnInfo> columnInfos;
	
	//constructor
	public TableInfo(
		final String tableId,
		final String tableName,
		final IContainer<IColumnInfo> columnInfos
	) {
		
		if (tableId == null) {
			throw ArgumentIsNullException.forArgumentName("table id");
		}
		
		if (tableName == null) {
			throw ArgumentIsNullException.forArgumentName("table name");
		}
		
		if (columnInfos == null) {
			throw ArgumentIsNullException.forArgumentName("column definitions");
		}
		
		this.tableId = tableId;
		this.tableName = tableName;
		this.columnInfos = columnInfos;
	}
	
	//method
	@Override
	public IColumnInfo getColumnInfoByColumnName(final String columnName) {
		return getColumnInfos().getRefFirst(cd -> cd.getColumnName().equals(columnName));
	}
	
	//method
	@Override
	public IContainer<IColumnInfo> getColumnInfos() {
		return columnInfos;
	}
	
	//method
	@Override
	public String getTableId() {
		return tableId;
	}
	
	//method
	@Override
	public String getTableName() {
		return tableName;
	}
	
	//method
	@Override
	public String getTableNameInQuotes() {
		return GlobalStringHelper.getInQuotes(getTableName());
	}
}
