//package declaration
package ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface IDatabaseDTO {
	
	//method declaration
	String getName();
	
	//method declaration
	IContainer<ITableDTO> getTables();
}
