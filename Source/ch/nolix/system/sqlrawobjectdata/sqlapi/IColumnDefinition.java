//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

//interface
public interface IColumnDefinition {
	
	//method declaration
	String getColumnName();
	
	//method declaration
	DataType getDataType();
}
