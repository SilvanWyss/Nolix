//package declaration
package ch.nolix.system.sqlrawobjectschema.schemawriter;

import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectschema.columnsystemtable.ColumnSystemTableColumn;
import ch.nolix.system.sqlrawobjectschema.columnsystemtable.ParametrizedPropertyTypeRecordMapper;
import ch.nolix.system.sqlrawobjectschema.databasepropertysystemtable.DatabaseProperty;
import ch.nolix.system.sqlrawobjectschema.databasepropertysystemtable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawobjectschema.tablesystemtable.TableSystemTableColumn;
import ch.nolix.system.sqlrawobjectschema.tablesystemtable.TableSystemTableRecordMapper;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
final class SystemDataWriterSQLStatementCreator {
	
	//static attribute
	private static final ParametrizedPropertyTypeRecordMapper parametrizedPropertyTypeRecordMapper =
	new ParametrizedPropertyTypeRecordMapper();
	
	//static attribute
	private static final TableSystemTableRecordMapper tableSystemTableRecordMapper =
	new TableSystemTableRecordMapper();
	
	//method
	public String createStatementToAddColumn(final String tableName, final IColumnDTO column) {
		
		final var parametrezidPropertyTypeRecord =
		parametrizedPropertyTypeRecordMapper.createParametrizedPropertyTypeRecordFrom(
			column.getParametrizedPropertyType()
		);
		
		return
	    "INSERT INTO "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " VALUES ('"
		+ tableName
		+ "', '"
		+ column.getHeader()
		+ "', "
		+ parametrezidPropertyTypeRecord.getPropertyTypeValue()
		+ ", "
		+ parametrezidPropertyTypeRecord.getDataTypeValue()
		+ ", "
		+ parametrezidPropertyTypeRecord.getReferencedTableValue()
		+ ", "
		+ parametrezidPropertyTypeRecord.getBackReferencedTableValue()
		+ ", "
		+ parametrezidPropertyTypeRecord.getBackReferencedColumnValue()
		+ ")";
	}
	
	//method
	public LinkedList<String> createStatementsToAddTable(final ITableDTO table) {
		
		final var statements = new LinkedList<String>();
		
		statements.addAtEnd(createStatementToAddTableIgnoringColumns(table));
		
		for (final var c : table.getColumns()) {
			statements.addAtEnd(createStatementToAddColumn(table.getName(), c));
		}
		
		return statements;
	}
	
	//method
	public String createStatementToDeleteColumn(final String tableName, final String columnHeader) {
		return
		"DELETE FROM "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " WHERE "
		+ ColumnSystemTableColumn.PARENT_TABLE.getLabel()
		+ " = "
		+ tableName
		+ " AND "
		+ ColumnSystemTableColumn.HEADER.getLabel()
		+ " = '"
		+ columnHeader
		+ "'"; 
	}
	
	//method
	public String createStatementToDeleteTable(final String tableName) {
		return 
		"DELETE FROM "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " WHERE "
		+ TableSystemTableColumn.NAME
		+ " = '"
		+ tableName
		+ "'";
	}
	
	//method
	public String createStatementToSetColumnHeader(String tableName, String columnHeader, String newColumnHeader) {
		return
	    "UPDATE "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " SET "
		+ ColumnSystemTableColumn.HEADER
		+ " = '"
		+ newColumnHeader
		+ "' WHERE "
		+ ColumnSystemTableColumn.PARENT_TABLE.getLabel()
		+ " = '"
		+ tableName
		+ "' AND "
		+ ColumnSystemTableColumn.HEADER.getLabel()
		+ " = '"
		+ columnHeader
		+ "'";
	}
	
	//method
	public String createStatementToSetColumnParametrizedPropertyType(
		final String tableName,
		final String columnHeader,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		
		final var parametrezidPropertyTypeRecord =
		parametrizedPropertyTypeRecordMapper.createParametrizedPropertyTypeRecordFrom(parametrizedPropertyType);
		
		return
		"UPDATE "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " SET "
		+ ColumnSystemTableColumn.DATA_TYPE
		+ " = "
		+ parametrezidPropertyTypeRecord.getDataTypeValue()
		+ ", "
		+ ColumnSystemTableColumn.REFERENCED_TABLE
		+ " = "
		+ parametrezidPropertyTypeRecord.getReferencedTableValue()
		+ ", "
		+ ColumnSystemTableColumn.BACK_REFERENCED_TABLE
		+ " = "
		+ parametrezidPropertyTypeRecord.getBackReferencedTableValue()
		+ "WHERE"
		+ ColumnSystemTableColumn.PARENT_TABLE
		+ " = '"
		+ tableName
		+ "' AND "
		+ ColumnSystemTableColumn.HEADER
		+ " = '"
		+ columnHeader
		+ "'";
	}
	
	//method
	public String createStatementToSetSchemaTimestamp(Time schemaTimestamp) {
		return
		"UPDATE "
		+ SystemDataTable.DATABASE_PROPERTY.getNameWithPrefix()
		+ " SET "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " = '"
		+ schemaTimestamp.getMilliseconds()
		+ "' WHERE "
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ " = "
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes();
	}
	
	//method
	public String createStatementToSetTableName(String tableName, String newTableName) {
		return
		"UPDATE "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " SET "
		+ TableSystemTableColumn.NAME.getLabel()
		+ " = '"
		+ newTableName
		+ "' WHERE "
		+ TableSystemTableColumn.NAME.getLabel()
		+ " = '"
		+ tableName
		+ "'";
	}
	
	//method
	private String createStatementToAddTableIgnoringColumns(ITableDTO table) {
		
		final var tableSystemTableRecord = tableSystemTableRecordMapper.createTableSystemTableRecordFrom(table);
		
		return
	    "INSERT INTO "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " VALUES ("
		+ tableSystemTableRecord.getNameValue()
		+ ")";
	}
}
