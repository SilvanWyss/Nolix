//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.system.sqlrawschema.columntable.ColumnTableColumn;
import ch.nolix.system.sqlrawschema.columntable.ParametrizedPropertyTypeSQLRecordMapper;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlrawschema.tabletable.TableTableRecordMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class SystemDataWriterSQLStatementCreator {
	
	//static attribute
	private static final ParametrizedPropertyTypeSQLRecordMapper parametrizedPropertyTypeSQLRecordMapper =
	new ParametrizedPropertyTypeSQLRecordMapper();
	
	//static attribute
	private static final TableTableRecordMapper tableSystemTableRecordMapper =
	new TableTableRecordMapper();
	
	//method
	public String createStatementToAddColumn(final String parentTableName, final IColumnDTO column) {
		
		final var parametrezidPropertyTypeRecord =
		parametrizedPropertyTypeSQLRecordMapper.createParametrizedPropertyTypeRecordFrom(
			column.getParametrizedPropertyType()
		);
		
		return
	    "INSERT INTO "
		+ SystemDataTable.COLUMN.getFullName()
		+ " ("
		+ ColumnTableColumn.ID.getName()
		+ ", "
		+ ColumnTableColumn.PARENT_TABLE_ID.getName()
		+ ", "
		+ ColumnTableColumn.NAME.getName()
		+ ", "
		+ ColumnTableColumn.PROPERTY_TYPE.getName()
		+ ", "
		+ ColumnTableColumn.DATA_TYPE.getName()
		+ ", "
		+ ColumnTableColumn.REFERENCED_TABLE_ID.getName()
		+ ", "
		+ ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
		+ ") SELECT '"
		+ column.getId()
		+ "', "
		+ TableTableColumn.ID.getFullName()
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
		+ SystemDataTable.TABLE.getFullName()
		+ " WHERE "
		+ TableTableColumn.NAME.getFullName()
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
		+ SystemDataTable.COLUMN.getFullName()
		+ " WHERE "
		+ ColumnTableColumn.PARENT_TABLE_ID.getName()
		+ " = "
		+ tableName
		+ " AND "
		+ ColumnTableColumn.NAME.getName()
		+ " = '"
		+ columnName
		+ "'"; 
	}
	
	//method
	public String createStatementToDeleteTable(final String tableName) {
		return 
		"DELETE FROM "
		+ SystemDataTable.TABLE.getFullName()
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
		+ SystemDataTable.COLUMN.getFullName()
		+ " SET "
		+ ColumnTableColumn.NAME
		+ " = '"
		+ newColumnName
		+ "' WHERE "
		+ ColumnTableColumn.PARENT_TABLE_ID.getName()
		+ " = '"
		+ tableName
		+ "' AND "
		+ ColumnTableColumn.NAME.getName()
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
		parametrizedPropertyTypeSQLRecordMapper.createParametrizedPropertyTypeRecordFrom(parametrizedPropertyType);
		
		return
		"UPDATE "
		+ SystemDataTable.COLUMN.getFullName()
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
	public String createStatementToSetSchemaTimestamp(ITime schemaTimestamp) {
		return
		"UPDATE "
		+ SystemDataTable.DATABASE_PROPERTY.getFullName()
		+ " SET "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " = '"
		+ schemaTimestamp.getSpecification().getSingleChildNodeHeader()
		+ "' WHERE "
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ " = "
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes();
	}
	
	//method
	public String createStatementToSetTableName(String tableName, String newTableName) {
		return
		"UPDATE "
		+ SystemDataTable.TABLE.getFullName()
		+ " SET "
		+ TableTableColumn.NAME.getName()
		+ " = '"
		+ newTableName
		+ "' WHERE "
		+ TableTableColumn.NAME.getName()
		+ " = '"
		+ tableName
		+ "'";
	}
	
	//method
	private String createStatementToAddTableIgnoringColumns(ITableDTO table) {
		
		final var tableSystemTableRecord = tableSystemTableRecordMapper.createTableSystemTableRecordFrom(table);
		
		return
	    "INSERT INTO "
		+ SystemDataTable.TABLE.getFullName()
		+ " ("
		+ TableTableColumn.ID.getName()
		+ ", "
		+ TableTableColumn.NAME.getName()
		+ ") VALUES ("
		+ tableSystemTableRecord.getIdValue()
		+ ", "
		+ tableSystemTableRecord.getNameValue()
		+ ")";
	}
}
