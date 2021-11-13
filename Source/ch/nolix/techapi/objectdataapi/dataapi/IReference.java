//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IReference<
	P extends IProperty<P>,
	E extends IEntity<E, P>>
extends IBaseReference<P, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesEntity();
}
