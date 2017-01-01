//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * An optional namable object is a namable object wose name can be removed dynamically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 * @param <ON> - The concrete type of an optional namable object.
 */
public interface OptionalNamable<ON extends OptionalNamable<ON>>
extends Namable<ON>{

	//abstract method
	/**
	 * @return true if this namable object has a name.
	 */
	public abstract boolean hasName();
	
	//default method
	/**
	 * @param name
	 * @return true if this optional namable object has the given name.
	 */
	public default boolean hasName(final String name) {
		
		//Handles the case if this optional namable object has no name.
		if (!hasName()) {
			return false;
		}
		
		//Handles the case if this optional namable object has a name.
		return getName().equals(name);
	}
	
	//abstract method
	/**
	 * Removes the name of this optional namable object.
	 * 
	 * @return this optional namable object.
	 */
	public abstract ON removeName();
}
