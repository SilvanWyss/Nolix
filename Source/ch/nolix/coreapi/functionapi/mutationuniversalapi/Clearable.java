//package declaration
package ch.nolix.coreapi.functionapi.mutationuniversalapi;

//own imports
import ch.nolix.coreapi.functionapi.requestuniversalapi.EmptinessRequestable;

//interface
/**
 * A {@link Clearable} can contain elements that can be removed.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
public interface Clearable extends EmptinessRequestable {
	
	//method declaration
	/**
	 * Removes the elements of the current {@link Clearable}.
	 */
	void clear();
}
