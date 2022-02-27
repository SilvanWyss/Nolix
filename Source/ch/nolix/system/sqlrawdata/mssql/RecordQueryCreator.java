//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IRecordQueryCreator;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class RecordQueryCreator implements IRecordQueryCreator {
	
	//method
	@Override
	public String createQueryToCountRecordsWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return "SELECT COUNT(" + columnName + ") FROM " + tableName + " WHERE "+ columnName + " = '" + value + "'";
	}
	
	//method
	@Override
	public String createQueryToLoadAllRecordsFromTable(final ITableInfo tableInfo) {
		return
		"SELECT Id, SaveStamp, "
		+ tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toString(", ")
		+ " FROM "
		+ tableInfo.getTableName();
	}
	
	//method
	@Override
	public String createQueryToLoadRecordFromTableById(String id, ITableInfo tableInfo) {
		return
		"SELECT Id, SaveStamp, "
		+ tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toString(", ")
		+ " FROM "
		+ tableInfo.getTableName()
		+ "WHERE Id = '"
		+ id
		+ "'";
	}
	
	//method
	@Override
	public String createQueryToLoadSchemaTimestamp() {
		return
		"SELECT "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " WHERE "
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ " = '"
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
		+ "'";
	}
}
