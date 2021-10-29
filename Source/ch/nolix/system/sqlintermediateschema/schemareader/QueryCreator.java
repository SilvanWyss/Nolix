//package declaration
package ch.nolix.system.sqlintermediateschema.schemareader;

//own imports
import ch.nolix.system.sqlintermediateschema.columnsystemtable.ColumnSystemTableColumn;
import ch.nolix.system.sqlintermediateschema.databasepropertysystemtable.DatabaseProperty;
import ch.nolix.system.sqlintermediateschema.databasepropertysystemtable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlintermediateschema.structure.SystemDataTable;
import ch.nolix.system.sqlintermediateschema.tablesystemtable.TableSystemTableColumn;

//class
final class QueryCreator {
	
	//method
	public String createQueryToLoadCoumns(final String tableName) {
		return
		"SELECT "
		+ ColumnSystemTableColumn.HEADER.getLabel()
		+ ", "
		+ ColumnSystemTableColumn.PARENT_TABLE.getLabel()
		+ ", "
		+ ColumnSystemTableColumn.PROPERTY_TYPE.getLabel()
		+ ", "
		+ ColumnSystemTableColumn.DATA_TYPE.getLabel()
		+ ", "
		+ ColumnSystemTableColumn.REFERENCED_TABLE.getLabel()
		+ ", "
		+ ColumnSystemTableColumn.BACK_REFERENCED_TABLE.getLabel()
		+ ", "
		+ ColumnSystemTableColumn.BACK_REFERENCED_COLUM.getLabel()
		+ " FROM "
		+ SystemDataTable.COLUMN.getNameWithPrefix()
		+ " WHERE "
		+ ColumnSystemTableColumn.PARENT_TABLE.getLabel()
		+ " = "
		+ tableName;
	}
	
	//method
	public String createQueryToLoadFlatTables() {
		return
		"SELECT "
		+ TableSystemTableColumn.NAME.getLabel()
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
