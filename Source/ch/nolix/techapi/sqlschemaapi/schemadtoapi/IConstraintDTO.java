//package declaration
package ch.nolix.techapi.sqlschemaapi.schemadtoapi;

//interface
public interface IConstraintDTO {
	
	//method declaration
	String getParameter();
	
	//method declaration
	ConstraintType getType();
	
	//method declaration
	boolean hasParameter();
}
