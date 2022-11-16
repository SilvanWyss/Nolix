//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//interface
public interface IRecordQueryCreator {
	
	//method declaration
	String createQueryToCountRecordsWithGivenValueAtGivenColumn(String tableName, String columnName, String value);
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(ITableInfo tableInfo);
	
	//method declaration
	String createQueryToLoadRecordFromTableById(String id, ITableInfo tableInfo);
	
	//method
	String createQueryToLoadSchemaTimestamp();
}
