//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

//interface
public interface IColumnDefinition {
	
	//method declaration
	DataType getColumnDataType();
	
	//method declaration
	String getColumnName();
}
