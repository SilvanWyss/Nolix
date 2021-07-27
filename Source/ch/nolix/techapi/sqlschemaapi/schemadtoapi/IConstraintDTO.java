//package declaration
package ch.nolix.techapi.sqlschemaapi.schemadtoapi;

//own imports
import ch.nolix.common.container.SingleContainer;

//interface
public interface IConstraintDTO {
	
	//method declaration
	SingleContainer<String> getOptionalParameter();
	
	//method declaration
	ConstraintType getType();
}
