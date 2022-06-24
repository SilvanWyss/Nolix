//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.IContainer;

//interface
public interface IConstraintDTO {
	
	//method declaration
	IContainer<String> getParameters();
	
	//method declaration
	ConstraintType getType();
}
