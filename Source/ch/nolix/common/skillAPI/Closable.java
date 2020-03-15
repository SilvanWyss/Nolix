//package declaration
package ch.nolix.common.skillAPI;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public interface Closable {
	
	//method
	/**
	 * @return the current {@link Closable} as {@link AutoCloseable}.
	 */
	public default AutoCloseable asAutoClosable() {
		return this::close;
	}
	
	//method declaration
	/**
	 * Closes the current {@link Closable}.
	 */
	public abstract void close();
	
	//method declaration
	/**
	 * @return true if the current {@link Closable} is closed.
	 */
	public abstract boolean isClosed();
	
	//method
	/**
	 * @return true if the current {@link Closable} is not closed.
	 */
	public default boolean isOpen() {
		return !isClosed();
	}
}
