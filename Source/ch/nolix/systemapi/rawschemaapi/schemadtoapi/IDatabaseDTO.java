//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

//own imports
import ch.nolix.core.container.IContainer;

//interface
public interface IDatabaseDTO {
	
	//method declaration
	String getName();
	
	//method declaration
	IContainer<ITableDTO> getTables();
}
