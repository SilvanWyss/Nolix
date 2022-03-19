//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi;

//own imports
import ch.nolix.core.container.IContainer;

//interface
public interface IColumnDTO {
	
	//method declaration
	IContainer<IConstraintDTO> getConstraints();
	
	//method declaration
	IDataTypeDTO getDataType();
	
	//method declaration
	String getName();
}
