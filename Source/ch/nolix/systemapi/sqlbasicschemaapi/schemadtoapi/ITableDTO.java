//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface ITableDTO {
	
	//method declaration
	IContainer<IColumnDTO> getColumns();
	
	//method declaration
	String getName();
}
