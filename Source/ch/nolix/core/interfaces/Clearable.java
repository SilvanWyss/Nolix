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
public interface Clearable {

	//abstract method
	/**
	 * Removes the elements of this clearable object from itself.
	 */
	public abstract void clear();
	
	//abstract method
	/**
	 * @return true if this clearable object contains one or several elements.
	 */
	public abstract boolean containsAny();
	
	//default method
	/**
	 * @return true if this clearable object contains no element.
	 */
	public default boolean isEmpty() {
		return !containsAny();
	}
}
