//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface Closable extends AutoCloseable {

	//abstract method
	/**
	 * @return true if this abortable object is not closed.
	 */
	public abstract boolean isAlive();
	
	//default method
	/**
	 * @return true if this closable object is closed.
	 */
	public default boolean isClosed() {
		return !isAlive();
	}
}
