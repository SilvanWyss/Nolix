//package declaration
package ch.nolix.system.sqlintermediateschema.schemawriter;

import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlintermediateschema.columnsystemtable.ColumnSystemTableColumn;
import ch.nolix.system.sqlintermediateschema.columnsystemtable.ParametrizedPropertyTypeRecordMapper;
import ch.nolix.system.sqlintermediateschema.databasepropertysystemtable.DatabaseProperty;
import ch.nolix.system.sqlintermediateschema.databasepropertysystemtable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlintermediateschema.structure.SystemTable;
import ch.nolix.system.sqlintermediateschema.tablesystemtable.TableSystemTableColumn;
import ch.nolix.system.sqlintermediateschema.tablesystemtable.TableSystemTableRecordMapper;
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
		+ SystemTable.COLUMN.getName()
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
		+ SystemTable.TABLE.getNameWithPrefix()
		+ " VALUES ("
		+ tableSystemTableRecord.getNameValue()
		+ ")";
	}
	
	//method
	public String createStatementToDeleteColumn(final String tableName, final String columnHeader) {
		return
		"DELETE FROM "
		+ SystemTable.COLUMN.getNameWithPrefix()
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
		+ SystemTable.TABLE.getNameWithPrefix()
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
		+ SystemTable.COLUMN.getNameWithPrefix()
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
		+ SystemTable.COLUMN.getNameWithPrefix()
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
		+ SystemTable.DATABASE_PROPERTY.getNameWithPrefix()
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
		+ SystemTable.TABLE.getNameWithPrefix()
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
}
