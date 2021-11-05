//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ITableDefinition {
	
	//method declaration
	IContainer<IColumnDefinition> getContentColumnDefinitions();
	
	//method declaration
	String getName();
}
