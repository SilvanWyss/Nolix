//package declaration
package ch.nolix.techapi.intermediatedataapi.recorddtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ITableDefinitionDTO {
	
	//method declaration
	IContainer<String> getContentColumnHeaders();
	
	//method declaration
	String getName();
}
