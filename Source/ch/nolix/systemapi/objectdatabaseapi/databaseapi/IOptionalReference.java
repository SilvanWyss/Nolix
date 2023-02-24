//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IOptionalReference<

	E extends IEntity
>
extends Clearable, IBaseReference<E> {
		
	//method declaration
	E getReferencedEntity();
	
	//method declaration
	String getReferencedEntityId();
	
	//method declaration
	void setEntity(E entity);
	
	//method declaration
	void setEntityById(final String id);
}
