//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * An abortable object can be aborted.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public interface Abortable extends AutoCloseable {
	
	//abstract method
	/**
	 * Aborts this abortable object.
	 */
	public abstract void abort();
	
	//abstract method
	/**
	 * Aborts this abortable object because of the given abort reason.
	 * 
	 * @param abortReason
	 */
	public abstract void abort(String abortReason);
	
	//default method
	/**
	 * Aborts this abortable object.
	 */
	public default void close() {
		abort();
	}
	
	//abstract method
	/**
	 * @return the abort reason of this abortable object.
	 */
	public abstract String getAbortReason();
	
	//abstract method
	/**
	 * @return true if this abortable object is aborted.
	 */
	public abstract boolean isAborted();
	
	//default method
	/**
	 * @return true if this abortable object is not aborted.
	 */
	public default boolean isRunning() {
		return !isAborted();
	}
}
