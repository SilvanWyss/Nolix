//package declaration
package ch.nolix.systemapi.rawdataapi.schemainfoapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ITableInfo {
	
	//method declaration
	IColumnInfo getColumnInfoByColumnName(String columnName);
	
	//method declaration
	IContainer<IColumnInfo> getColumnInfos();
	
	//method declaration
	String getTableId();
	
	//method declaration
	String getTableName();
	
	//method declaration
	String getTableNameInQuotes();
}
