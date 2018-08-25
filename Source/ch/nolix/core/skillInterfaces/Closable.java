//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface Closable extends AutoCloseable {

	//default method
	/**
	 * @return true if this abortable object is not closed.
	 */
	public default boolean isAlive() {
		return !isClosed();
	}
	
	//abstract method
	/**
	 * @return true if this closable object is closed.
	 */
	public abstract boolean isClosed();
}
