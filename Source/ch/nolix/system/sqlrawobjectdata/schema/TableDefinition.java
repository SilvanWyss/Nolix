//package declaration
package ch.nolix.system.sqlrawobjectdata.schema;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;

//class
public final class TableDefinition implements ITableDefinition {
	
	//attribute
	private final String tableId;
	
	//attribute
	private final String tableName;
	
	//multi-attribute
	private final IContainer<IColumnDefinition> columnDefinitions;
	
	//constructor
	public TableDefinition(
		final String tableId,
		final String tableName,
		final IContainer<IColumnDefinition> columnDefinitions
	) {
		
		if (tableId == null) {
			throw new ArgumentIsNullException("table id");
		}
		
		if (tableName == null) {
			throw new ArgumentIsNullException("table name");
		}
		
		if (columnDefinitions == null) {
			throw new ArgumentIsNullException("column definitions");
		}
		
		this.tableId = tableId;
		this.tableName = tableName;
		this.columnDefinitions = columnDefinitions;
	}
	
	//method
	@Override
	public IContainer<IColumnDefinition> getColumnDefinitions() {
		return columnDefinitions;
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
