//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IBaseBackReference<IMPL, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesBackEntity();
}
