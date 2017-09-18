//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A clearable object is an object
 * that contains elements that can be removed.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 30
 * @param <C> The type of a clearable object.
 */
public interface Clearable<C extends Clearable<C>> {

	//abstract method
	/**
	 * Removes the elements of this clearable object from itself.
	 * 
	 * @return this clearable object.
	 */
	public abstract C clear();
	
	//default method
	/**
	 * @return true if this clearable object contains one or several elements.
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
