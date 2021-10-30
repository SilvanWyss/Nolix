//package declaration
package ch.nolix.techapi.rawobjectschemaapi.schemadtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IDatabaseDTO {
	
	//method declaration
	String getName();
	
	//method declaration
	IContainer<ITableDTO> getTables();
}
