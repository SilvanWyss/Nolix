//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ISchema<IMPL> {
	
	//method declaration
	IContainer<Class<IEntity<IMPL>>> getEntityTypesInOrder();
}
