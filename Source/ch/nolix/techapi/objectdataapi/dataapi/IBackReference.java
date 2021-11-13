//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBackReference<
	P extends IProperty<P>,
	E extends IEntity<E, P>
> extends IBaseBackReference<P, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesBackEntity();
}
