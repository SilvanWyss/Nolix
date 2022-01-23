//package declaration
package ch.nolix.system.sqlrawobjectschema.schemawriter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectschema.columntable.ColumnTableColumn;
import ch.nolix.system.sqlrawobjectschema.columntable.ParametrizedPropertyTypeRecordMapper;
import ch.nolix.system.sqlrawobjectschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawobjectschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawobjectschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlrawobjectschema.tabletable.TableTableRecordMapper;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
final class SystemDataWriterSQLStatementCreator {
	
	//static attribute
	private static final ParametrizedPropertyTypeRecordMapper parametrizedPropertyTypeRecordMapper =
	new ParametrizedPropertyTypeRecordMapper();
	
	//static attribute
	private static final TableTableRecordMapper tableSystemTableRecordMapper =
	new TableTableRecordMapper();
	
	//method
	public String createStatementToAddColumn(final String parentTableName, final IColumnDTO column) {
		
		final var parametrezidPropertyTypeRecord =
		parametrizedPropertyTypeRecordMapper.createParametrizedPropertyTypeRecordFrom(
			column.getParametrizedPropertyType()
		);
		
		return
	    "INSERT INTO "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " ("
		+ ColumnTableColumn.ID.getLabel()
		+ ", "
		+ ColumnTableColumn.PARENT_TABLE_ID.getLabel()
		+ ", "
		+ ColumnTableColumn.NAME.getLabel()
		+ ", "
		+ ColumnTableColumn.PROPERTY_TYPE.getLabel()
		+ ", "
		+ ColumnTableColumn.DATA_TYPE.getLabel()
		+ ", "
		+ ColumnTableColumn.REFERENCED_TABLE_ID.getLabel()
		+ ", "
		+ ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getLabel()
		+ ") SELECT '"
		+ column.getId()
		+ "', "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ "."
		+ TableTableColumn.ID.getLabel()
		+ ", '"
		+ column.getName()
		+ "', "
		+ parametrezidPropertyTypeRecord.getPropertyTypeValue()
		+ ", "
		+ parametrezidPropertyTypeRecord.getDataTypeValue()
		+ ", "
		+ parametrezidPropertyTypeRecord.getReferencedTableIdValue()
		+ ", "
		+ parametrezidPropertyTypeRecord.getBackReferencedColumnIdValue()
		+ " FROM "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " WHERE "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ "."
		+ TableTableColumn.NAME.getLabel()
		+ " = '"
		+ parentTableName
		+ "'" ;
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
	public String createStatementToDeleteColumn(final String tableName, final String columnName) {
		return
		"DELETE FROM "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " WHERE "
		+ ColumnTableColumn.PARENT_TABLE_ID.getLabel()
		+ " = "
		+ tableName
		+ " AND "
		+ ColumnTableColumn.NAME.getLabel()
		+ " = '"
		+ columnName
		+ "'"; 
	}
	
	//method
	public String createStatementToDeleteTable(final String tableName) {
		return 
		"DELETE FROM "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " WHERE "
		+ TableTableColumn.NAME
		+ " = '"
		+ tableName
		+ "'";
	}
	
	//method
	public String createStatementToSetColumnName(String tableName, String columnName, String newColumnName) {
		return
	    "UPDATE "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " SET "
		+ ColumnTableColumn.NAME
		+ " = '"
		+ newColumnName
		+ "' WHERE "
		+ ColumnTableColumn.PARENT_TABLE_ID.getLabel()
		+ " = '"
		+ tableName
		+ "' AND "
		+ ColumnTableColumn.NAME.getLabel()
		+ " = '"
		+ columnName
		+ "'";
	}
	
	//method
	public String createStatementToSetColumnParametrizedPropertyType(
		final String columnID,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		
		final var parametrezidPropertyTypeRecord =
		parametrizedPropertyTypeRecordMapper.createParametrizedPropertyTypeRecordFrom(parametrizedPropertyType);
		
		return
		"UPDATE "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " SET "
		+ ColumnTableColumn.DATA_TYPE
		+ " = "
		+ parametrezidPropertyTypeRecord.getDataTypeValue()
		+ ", "
		+ ColumnTableColumn.REFERENCED_TABLE_ID
		+ " = "
		+ parametrezidPropertyTypeRecord.getReferencedTableIdValue()
		+ ", "
		+ ColumnTableColumn.BACK_REFERENCED_COLUM_ID
		+ " = "
		+ parametrezidPropertyTypeRecord.getBackReferencedColumnIdValue()
		+ "WHERE"
		+ ColumnTableColumn.ID
		+ " = '"
		+ columnID
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
		+ TableTableColumn.NAME.getLabel()
		+ " = '"
		+ newTableName
		+ "' WHERE "
		+ TableTableColumn.NAME.getLabel()
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
		+ " ("
		+ TableTableColumn.ID.getLabel()
		+ ", "
		+ TableTableColumn.NAME.getLabel()
		+ ") VALUES ("
		+ tableSystemTableRecord.getIdValue()
		+ ", "
		+ tableSystemTableRecord.getNameValue()
		+ ")";
	}
}
