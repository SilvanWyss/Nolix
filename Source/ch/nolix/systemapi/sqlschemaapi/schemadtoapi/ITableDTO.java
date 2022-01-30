//package declaration
package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface ITableDTO {
	
	//method declaration
	IContainer<IColumnDTO> getColumns();
	
	//method declaration
	String getName();
}
