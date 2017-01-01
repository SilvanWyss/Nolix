//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * A clearable object can be cleared.
 * Clearing means making empty.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 30
 * @param <C> - The concrete type of a clearable object.
 */
public interface Clearable<C extends Clearable<C>> {

	//abstract method
	/**
	 * Clears this clearable object.
	 */
	public abstract C clear();
	
	//default method
	/**
	 * @return true if this clearable object contains any element.
	 */
	public default boolean containsAny() {
		return !isEmpty();
	}
	
	//abstract method
	/**
	 * @return true if this clearable object contains no element.
	 */
	public abstract boolean isEmpty();
}
