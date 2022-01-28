//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ITableDefinition {
	
	//method declaration
	IContainer<IColumnDefinition> getColumnDefinitions();
	
	//method declaration
	String getTableId();
	
	//method declaration
	String getTableName();
}
