//package declaration
package ch.nolix.common.skillAPI;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public interface OptionalClosable {
	
	//abstract method
	/**
	 * Closes the current {@link OptionalClosable}.
	 */
	public abstract void close();
	
	//abstract method
	/**
	 * @return true if the current {@link OptionalClosable} is closed.
	 */
	public abstract boolean isClosed();
	
	//default method
	/**
	 * @return true if the current {@link OptionalClosable} is not closed.
	 */
	public default boolean isOpen() {
		return !isClosed();
	}
}
