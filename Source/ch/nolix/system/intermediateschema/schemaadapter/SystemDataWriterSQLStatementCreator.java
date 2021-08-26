//package declaration
package ch.nolix.system.intermediateschema.schemaadapter;

import ch.nolix.element.time.base.Time;
//own imports
import ch.nolix.system.intermediateschema.columnsystemtable.ColumnSystemTableColumn;
import ch.nolix.system.intermediateschema.columnsystemtable.ParametrizedPropertyTypeRecordMapper;
import ch.nolix.system.intermediateschema.databasepropertysystemtable.DatabaseProperty;
import ch.nolix.system.intermediateschema.databasepropertysystemtable.DatabasePropertySystemTableColumn;
import ch.nolix.system.intermediateschema.tablesystemtable.TableSystemTableColumn;
import ch.nolix.system.intermediateschema.tablesystemtable.TableSystemTableRecordMapper;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

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
		+ SystemDataTable.COLUMN.getName()
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
	public String createStatementToAddTable(final ITableDTO table) {
		
		final var tableSystemTableRecord = tableSystemTableRecordMapper.createTableSystemTableRecordFrom(table);
		
		return
	    "INSERT INTO "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " VALUES ("
		+ tableSystemTableRecord.getNameValue()
		+ ")";
	}
	
	//method
	public String createStatementToDeleteColumn(final String tableName, final String columnHeader) {
		return
		"DELETE FROM "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " WHERE "
		+ ColumnSystemTableColumn.TABLE.getName()
		+ " = "
		+ tableName
		+ " AND "
		+ ColumnSystemTableColumn.HEADER.getName()
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
		+ ColumnSystemTableColumn.TABLE.getName()
		+ " = '"
		+ tableName
		+ "' AND "
		+ ColumnSystemTableColumn.HEADER.getName()
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
		+ ColumnSystemTableColumn.TABLE
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
		+ TableSystemTableColumn.NAME.getName()
		+ " = '"
		+ newTableName
		+ "' WHERE "
		+ TableSystemTableColumn.NAME.getName()
		+ " = '"
		+ tableName
		+ "'";
	}
}
