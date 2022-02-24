//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface IConstraintDTO {
	
	//method declaration
	IContainer<String> getParameters();
	
	//method declaration
	ConstraintType getType();
}
