//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.container.IContainer;

//interface
public interface ISchema<IMPL> {
	
	//method declaration
	IContainer<Class<IEntity<IMPL>>> getEntityTypesInOrder();
}
