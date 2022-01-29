//package declaration
package ch.nolix.system.sqlrawobjectdata.schemainfo;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

//class
public final class TableDefinition implements ITableInfo {
	
	//attribute
	private final String tableId;
	
	//attribute
	private final String tableName;
	
	//multi-attribute
	private final IContainer<IColumnInfo> columnInfos;
	
	//constructor
	public TableDefinition(
		final String tableId,
		final String tableName,
		final IContainer<IColumnInfo> columnInfos
	) {
		
		if (tableId == null) {
			throw new ArgumentIsNullException("table id");
		}
		
		if (tableName == null) {
			throw new ArgumentIsNullException("table name");
		}
		
		if (columnInfos == null) {
			throw new ArgumentIsNullException("column definitions");
		}
		
		this.tableId = tableId;
		this.tableName = tableName;
		this.columnInfos = columnInfos;
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
}
