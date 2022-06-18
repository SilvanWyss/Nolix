//package declaration
package ch.nolix.core.requestuniversalapi;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link EmptinessRequestable} can be asked if it is empty or contains elements.
 * 
 * @author Silvan Wyss
 * @date 2020-06-11
 */
@AllowDefaultMethodsAsDesignPattern
public interface EmptinessRequestable {
	
	//method
	/**
	 * @throws NonEmptyArgumentException if the current {@link EmptinessRequestable} is not empty.
	 */
	default void assertIsEmpty() {
		if (containsAny()) {
			throw new NonEmptyArgumentException(this);
		}
	}
	
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
