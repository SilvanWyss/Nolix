//package declaration
package ch.nolix.common.requestapi;

//interface
/**
 * A {@link EmptinessRequestable} can be asked if it is empty or contains elements.
 * 
 * @author Silvan Wyss
 * @date 2020-06-11
 * @lines 20
 */
public interface EmptinessRequestable {
	
	//method
	/**
	 * @return true if the current {@link EmptinessRequestable} contains one or several elements.
	 */
	default boolean containsAny() {
		return !isEmpty();
	}
	
	//method declaration
	/**
	 * @return true if {@link EmptinessRequestable} does not contain an element.
	 */
	boolean isEmpty();
}
