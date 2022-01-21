//package declaration
package ch.nolix.system.sqlrawobjectschema.schemareader;

//own imports
import ch.nolix.system.sqlrawobjectschema.columntable.ColumnTableColumn;
import ch.nolix.system.sqlrawobjectschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawobjectschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawobjectschema.tabletable.TableTableColumn;

//class
final class QueryCreator {
	
	//method
	public String createQueryToLoadCoumnsByTableId(final String tableId) {
		return
		"SELECT "
		+ ColumnTableColumn.NAME.getLabel()
		+ ", "
		+ ColumnTableColumn.PARENT_TABLE.getLabel()
		+ ", "
		+ ColumnTableColumn.PROPERTY_TYPE.getLabel()
		+ ", "
		+ ColumnTableColumn.DATA_TYPE.getLabel()
		+ ", "
		+ ColumnTableColumn.REFERENCED_TABLE.getLabel()
		+ ", "
		+ ColumnTableColumn.BACK_REFERENCED_TABLE.getLabel()
		+ ", "
		+ ColumnTableColumn.BACK_REFERENCED_COLUM.getLabel()
		+ " FROM "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " WHERE "
		+ ColumnTableColumn.PARENT_TABLE.getLabel()
		+ " = '"
		+ tableId
		+ "'";
	}
	
	//method
	public String createQueryToLoadCoumnsByTableName(final String tableName) {
		return
		"SELECT "
		+ ColumnTableColumn.NAME.getLabel()
		+ ", "
		+ ColumnTableColumn.PARENT_TABLE.getLabel()
		+ ", "
		+ ColumnTableColumn.PROPERTY_TYPE.getLabel()
		+ ", "
		+ ColumnTableColumn.DATA_TYPE.getLabel()
		+ ", "
		+ ColumnTableColumn.REFERENCED_TABLE.getLabel()
		+ ", "
		+ ColumnTableColumn.BACK_REFERENCED_TABLE.getLabel()
		+ ", "
		+ ColumnTableColumn.BACK_REFERENCED_COLUM.getLabel()
		+ " FROM "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " WHERE "
		+ ColumnTableColumn.PARENT_TABLE.getLabel()
		+ " = '"
		+ tableName
		+ "'";
	}
	
	//method
	public String createQueryToLoadFlatTableById(final String id) {
		return
		"SELECT "
		+ TableTableColumn.ID.getLabel()
		+ ", "
		+ TableTableColumn.NAME.getLabel()
		+ " FROM "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " WHERE "
		+ TableTableColumn.ID.getLabel()
		+ " = '"
		+ id
		+ "'";
	}
	
	//method
	public String createQueryToLoadFlatTableByName(final String name) {
		return
		"SELECT "
		+ TableTableColumn.ID.getLabel()
		+ ", "
		+ TableTableColumn.NAME.getLabel()
		+ " FROM "
		+ SystemDataTable.TABLE.getNameWithPrefix()
		+ " WHERE "
		+ TableTableColumn.NAME.getLabel()
		+ " = '"
		+ name
		+ "'";
	}
	
	//method
	public String createQueryToLoadFlatTables() {
		return
		"SELECT "
		+ TableTableColumn.ID.getLabel()
		+ ", "
		+ TableTableColumn.NAME.getLabel()
		+ " FROM "
		+ SystemDataTable.TABLE.getNameWithPrefix();
	}
	
	//method
	public String createQueryToLoadSchemaTimestamp() {
		return
		"SELECT "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " FROM "
		+ SystemDataTable.DATABASE_PROPERTY.getNameWithPrefix()
		+ " WHERE "
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ " = "
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes();
	}
}
