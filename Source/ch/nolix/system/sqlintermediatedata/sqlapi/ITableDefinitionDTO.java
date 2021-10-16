//package declaration
package ch.nolix.system.sqlintermediatedata.sqlapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ITableDefinitionDTO {
	
	//method declaration
	IContainer<String> getContentColumnHeaders();
	
	//method declaration
	String getName();
}
