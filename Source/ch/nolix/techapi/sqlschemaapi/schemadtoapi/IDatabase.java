//package declaration
package ch.nolix.techapi.sqlschemaapi.schemadtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IDatabase {
	
	//method declaration
	String getName();
	
	//method declaration
	IContainer<ITableDTO> getTables();
}
