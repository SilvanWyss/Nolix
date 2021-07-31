//package declaration
package ch.nolix.system.sqlschema.mssqllanguage;

//own imports
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IDataTypeDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.techapi.sqlschemaapi.schemalanguageapi.ISchemaStatementCreator;

//class
public class MSSQLSchemaStatementCreator implements ISchemaStatementCreator {
	
	//method
	@Override
	public String createStatementToAddColumn(final String tabbleName, final IColumnDTO column) {
		return ("ALTER TABLE " + tabbleName + " ADD " + getColumnAsSQL(column));
	}
	
	//method
	@Override
	public String createStatementToAddTable(ITableDTO table) {
		return 
		"CREATE TABLE " + table.getName() + " (" + table.getColumns().to(this::getColumnAsSQL).toString(",") + ")";
	}
	
	//method
	@Override
	public String createStatementToDeleteColumn(final String tableName, final String columnName) {
		return ("ALTER TABLE " + tableName + " DROP COLUMN " + columnName);
	}
	
	//method
	@Override
	public String createStatementToDeleteTable(final String tableName) {
		return ("DROP TABLE " + tableName);
	}
	
	//method
	@Override
	public String createStatementToRenameColumn(
		final String tableName,
		final String columnName,
		final String newColumnName
	) {
		return ("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName);
	}
	
	//method
	@Override
	public String createStatementToRenameTable(final String tableName, final String newTableName) {
		return ("ALTER TABLE " + tableName + " RENAME TO " + newTableName);
	}
	
	//method
	private String getColumnAsSQL(final IColumnDTO column) {
		return (column.getName() + " " + getDataTypeAsSQL(column.getDataType()));
	}
	
	//method
	private String getDataTypeAsSQL(final IDataTypeDTO dataType) {
		
		if (!dataType.hasParameter()) {
			return dataType.getName();
		}
		
		return (dataType.getName() + "(" + dataType.getParameter() + ")");
	}
}
