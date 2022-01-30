//package declaration
package ch.nolix.systemapi.rawobjectdataapi.schemainfoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface ITableInfo {
	
	//method
	default IColumnInfo getColumnInfoByColumnName(String columnName) {
		return getColumnInfos().getRefFirst(cd -> cd.getColumnName().equals(columnName));
	}
	
	//method declaration
	IContainer<IColumnInfo> getColumnInfos();
	
	//method declaration
	String getTableId();
	
	//method declaration
	String getTableName();
}
