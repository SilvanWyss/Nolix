//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IQueryCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;

//class
public final class QueryCreator implements IQueryCreator {
	
	//method
	@Override
	public String createQueryToCountRecordsWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnHeader,
		final String value
	) {
		return "SELECT COUNT(" + columnHeader + ") FROM " + tableName + " WHERE "+ columnHeader + " = '" + value + "'";
	}
	
	//method
	@Override
	public String createQueryToLoadAllRecordsFromTable(final ITableDefinition tableDefinition) {
		return
		"SELECT Id, SaveStamp, "
		+ tableDefinition.getContentColumnDefinitions().to(IColumnDefinition::getColumnHeader).toString(", ")
		+ " FROM "
		+ tableDefinition.getName();
	}
	
	//method
	@Override
	public String createQueryToLoadRecordFromTableById(String id, ITableDefinition tableDefinition) {
		return
		"SELECT Id, SaveStamp, "
		+ tableDefinition.getContentColumnDefinitions().to(IColumnDefinition::getColumnHeader).toString(", ")
		+ " FROM "
		+ tableDefinition.getName()
		+ "WHERE Id = '"
		+ id
		+ "'";
	}
}
