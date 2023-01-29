//package declaration
package ch.nolix.systemapi.objectdatabaseapi.schemaapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;

//interface
public interface ISchema<IMPL> {
	
	//method declaration
	Class<? extends IEntity<IMPL>> getEntityTypeByName(String name);
	
	//method declaration
	IContainer<Class<? extends IEntity<IMPL>>> getEntityTypes();
}
