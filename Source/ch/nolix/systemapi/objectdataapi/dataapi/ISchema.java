//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ISchema<IMPL> {
	
	//method declaration
	IContainer<Class<? extends IEntity<IMPL>>> getEntityTypesInOrder();
}
