//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * An optional namable object is a namable object
 * whose name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 * @param <ON> The type of an optional namable object.
 */
public interface OptionalNamable<ON extends OptionalNamable<ON>>
extends Namable<ON>{

	//abstract method
	/**
	 * @return true if this optional namable object has a name.
	 */
	public abstract boolean hasName();
	
	//default method
	/**
	 * @param name
	 * @return true if this optional namable object has the given name.
	 */
	@Override
	public default boolean hasName(final String name) {
		
		//Handles the case that this optional namable object does not have a name.
		if (!hasName()) {
			return false;
		}
		
		//Handles the case that this optional namable object has a name.
		return getName().equals(name);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if this optional namable object has the same name as the given object.
	 */
	public default boolean hasSameNameAs(final OptionalNamable<?> object) {
		
		//Handles the case that the given object does not have a name.
		if (!object.hasName()) {
			return false;
		}
		
		//Handles the case that the given object has a name.
		return hasName(object.getName());
	}
	
	//abstract method
	/**
	 * Removes the name of this optional namable object.
	 * 
	 * @return this optional namable object.
	 */
	public abstract ON removeName();
}
