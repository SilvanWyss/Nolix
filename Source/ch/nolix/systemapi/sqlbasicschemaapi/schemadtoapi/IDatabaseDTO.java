//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi;

//own imports
import ch.nolix.core.containerapi.IContainer;

//interface
public interface IDatabaseDTO {
	
	//method declaration
	String getName();
	
	//method declaration
	IContainer<ITableDTO> getTables();
}
