//package declaration
package ch.nolix.system.noderawobjectdata.tabledefinition;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

//class
public final class TableInfo implements ITableInfo {
	
	//attribute
	private final String tableId;
	
	//attribute
	private final String tableName;
	
	//multi-attribute
	private final IContainer<IColumnInfo> columnInfos;
	
	//constructor
	public TableInfo(final String tableId, final String tableName, IContainer<IColumnInfo> contentColumnDefinitions) {
		
		if (tableId == null) {
			throw new ArgumentIsNullException("table id");
		}
		
		if (tableName == null) {
			throw new ArgumentIsNullException("table name");
		}
		
		if (contentColumnDefinitions == null) {
			throw new ArgumentIsNullException("content column definitions");
		}
		
		this.tableId = tableId;
		this.tableName = tableName;
		this.columnInfos = contentColumnDefinitions;
	}
	
	//method
	public int getColumnCount() {
		return columnInfos.getElementCount();
	}
	
	//method
	@Override
	public IContainer<IColumnInfo> getColumnInfos() {
		return columnInfos;
	}
	
	//method
	public int getIndexOfColumnByColumnName(final String columnName) {
		return columnInfos.getIndexOfFirst(ci -> ci.getColumnName().equals(columnName));
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
}
