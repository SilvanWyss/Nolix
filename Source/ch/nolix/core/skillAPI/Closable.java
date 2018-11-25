//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface Closable extends AutoCloseable {

	//default method
	/**
	 * @return true if the current {@link Closable} is not closed.
	 */
	public default boolean isAlive() {
		return !isClosed();
	}
	
	//abstract method
	/**
	 * @return true if the current {@link Closable} is closed.
	 */
	public abstract boolean isClosed();
}
