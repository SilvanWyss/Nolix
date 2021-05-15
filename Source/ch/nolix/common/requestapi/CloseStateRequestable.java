//package declaration
package ch.nolix.common.requestapi;

//interface
/**
 * A {@link CloseStateRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 * @date 2020-07-01
 * @lines 20
 */
public interface CloseStateRequestable {
	
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
