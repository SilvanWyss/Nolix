//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.IContainer;

//interface
public interface IDatabaseDTO {
	
	//method declaration
	String getName();
	
	//method declaration
	IContainer<ITableDTO> getTables();
}
