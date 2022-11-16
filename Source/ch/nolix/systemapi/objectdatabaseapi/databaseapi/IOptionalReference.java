//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IOptionalReference<
	IMPL,
	E extends IEntity<IMPL>>
extends Clearable, IBaseReference<IMPL, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	void setEntity(E entity);
	
	//method declaration
	void setEntityWithId(final String id);
}
