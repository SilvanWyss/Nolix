//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ITableDTO {
	
	//method declaration
	IContainer<IColumnDTO> getColumns();
	
	//method declaration
	String getName();
}
