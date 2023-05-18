//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IColumnDTO {
	
	//method declaration
	IContainer<IConstraintDTO> getConstraints();
	
	//method declaration
	IDataTypeDTO getDataType();
	
	//method declaration
	String getName();
}
