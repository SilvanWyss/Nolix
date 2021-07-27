//package declaration
package ch.nolix.techapi.sqlschemaapi.schemadtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IColumnDTO {
	
	//method declaration
	IContainer<IConstraintDTO> getConstraints();
	
	//method declaration
	String getDataType();
	
	//method declaration
	String getName();
}
