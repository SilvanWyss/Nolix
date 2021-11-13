//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalReference<
	P extends IProperty<P>,
	E extends IEntity<E, P>>
extends Clearable, IBaseReference<P, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesEntity();
}
