//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public interface Closable extends AutoCloseable {
	
	//abstract method
	/**
	 * Closes this closable object.
	 */
	public abstract void close();
	
	//abstract method
	/**
	 * @return true if this closable object is closed.
	 */
	public abstract boolean isClosed();
	
	//default method
	/**
	 * @return true if this abortable object is not closed.
	 */
	public default boolean isAlive() {
		return !isClosed();
	}
}
