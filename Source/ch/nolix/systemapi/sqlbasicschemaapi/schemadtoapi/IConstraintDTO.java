//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi;

//own imports
import ch.nolix.core.containerapi.IContainer;

//interface
public interface IConstraintDTO {
	
	//method declaration
	IContainer<String> getParameters();
	
	//method declaration
	ConstraintType getType();
}
