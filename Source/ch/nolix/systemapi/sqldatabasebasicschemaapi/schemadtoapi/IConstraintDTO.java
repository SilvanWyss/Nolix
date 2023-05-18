//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IConstraintDTO {
	
	//method declaration
	IContainer<String> getParameters();
	
	//method declaration
	ConstraintType getType();
}
