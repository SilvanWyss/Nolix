//package declaration
package ch.nolix.core.requestuniversalapi;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link CloseStateRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 * @date 2020-07-01
 */
@AllowDefaultMethodsAsDesignPattern
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
