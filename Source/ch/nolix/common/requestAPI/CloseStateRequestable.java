//package declaration
package ch.nolix.common.requestAPI;

//interface
/**
 * A {@link CloseStateRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 * @month 2020-07
 * @lines 20
 */
public interface CloseStateRequestable {
	
	//method declaration
	/**
	 * @return true if the current {@link CloseStateRequestable} is closed.
	 */
	public abstract boolean isClosed();
	
	//method
	/**
	 * @return true if the current {@link CloseStateRequestable} is not closed.
	 */
	public default boolean isOpen() {
		return !isClosed();
	}
}
