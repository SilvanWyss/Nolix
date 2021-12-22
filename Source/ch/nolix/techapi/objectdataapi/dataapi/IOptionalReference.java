//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

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
}
