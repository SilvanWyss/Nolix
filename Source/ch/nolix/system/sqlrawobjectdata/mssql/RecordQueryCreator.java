//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordQueryCreator;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

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
}
