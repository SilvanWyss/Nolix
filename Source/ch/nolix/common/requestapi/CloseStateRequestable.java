//package declaration
package ch.nolix.common.requestapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;

//interface
/**
 * A {@link CloseStateRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 * @date 2020-07-01
 * @lines 40
 */
public interface CloseStateRequestable {
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link CloseStateRequestable} is closed.
	 */
	default void assertIsOpen() {
		
		//Asserts that the current CloseStateRequestable is open.
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//method declaration
	/**
	 * @return true if the current {@link CloseStateRequestable} is closed.
	 */
	boolean isClosed();
	
	//method
	/**
	 * @return true if the current {@link CloseStateRequestable} is not closed.
	 */
	default boolean isOpen() {
		return !isClosed();
	}
}
