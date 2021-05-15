//package declaration
package ch.nolix.common.skillapi;

//own imports
import ch.nolix.common.requestapi.EmptinessRequestable;

//interface
/**
 * A {@link Clearable} can contain elements that can be removed.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 * @lines 20
 */
public interface Clearable extends EmptinessRequestable {
	
	//method declaration
	/**
	 * Removes the elements of the current {@link Clearable}.
	 */
	void clear();
}
