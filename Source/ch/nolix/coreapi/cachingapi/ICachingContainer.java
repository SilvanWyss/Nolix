//package declaration
package ch.nolix.coreapi.cachingapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ICachingContainer<E> extends IContainer<E> {
	
	//method declaration
	boolean containsWithId(String id);
	
	//method declaration
	String getIdOf(E element);
	
	//method declaration
	E getRefById(String id);
	
	//method declaration
	String registerAndGetId(E element);
	
	//method declaration
	void registerAtId(String id, E element);
	
	//method declaration
	String registerIfNotRegisteredAndGetId(E element);
}
