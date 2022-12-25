//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ISchema<IMPL> {
	
	//method declaration
	Class<? extends IEntity<IMPL>> getEntityTypeByName(String name);
	
	//method declaration
	IContainer<Class<? extends IEntity<IMPL>>> getEntityTypes();
}
