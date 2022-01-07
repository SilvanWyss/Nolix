//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//own imports
import ch.nolix.techapi.databaseapi.datatypeapi.DataType;

//interface
public interface IColumnDefinition {
	
	//method declaration
	String getColumnName();
	
	//method declaration
	DataType getDataType();
}
