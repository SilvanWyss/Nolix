//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

//interface
public interface IRecordQueryCreator {
	
	//method declaration
	String createQueryToCountRecordsWithGivenValueAtGivenColumn(String tableName, String columnName, String value);
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(ITableInfo tableInfo);
	
	//method declaration
	String createQueryToLoadRecordFromTableById(String id, ITableInfo tableInfo);
}
