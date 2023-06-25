//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IDatabaseDto {
	
	//method declaration
	String getName();
	
	//method declaration
	IContainer<ITableDto> getTables();
}