//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.skillapi.Clearable;

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
