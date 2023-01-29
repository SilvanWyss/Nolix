//package declaration
package ch.nolix.coreapi.containerapi.singlecontainerapi;

//own imports
import ch.nolix.coreapi.functionapi.requestuniversalapi.EmptinessRequestable;

//interface
public interface ISingleContainer<E> extends EmptinessRequestable {
	
	//method declaration
	E getRefElement();
}
