//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * An {@link OptionalNamable} is a {@link Namable} whose name can be removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 * @param <ON> The type of a {@link OptionalNamable}.
 */
public interface OptionalNamable<ON extends OptionalNamable<ON>> extends Namable<ON>{
	
	//abstract method
	/**
	 * @return true if the current {@link OptionalNamable} has a name.
	 */
	public abstract boolean hasName();
	
	//default method
	/**
	 * @param name
	 * @return true if the current {@link OptionalNamable} has the given name.
	 */
	@Override
	public default boolean hasName(final String name) {
		
		//Handles the case that the current OptionalNamable does not have a name.
		if (!hasName()) {
			return false;
		}
		
		//Handles the case that the current OptionalNamable has a name.
		return getName().equals(name);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link OptionalNamable} has the same name as the given object.
	 */
	public default boolean hasSameNameAs(final OptionalNamable<?> object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a name.
		if (!object.hasName()) {
			return false;
		}
		
		//Calls other method.
		return hasName(object.getName());
	}
	
	//abstract method
	/**
	 * Removes the name of the current {@link OptionalNamable}.
	 * 
	 * @return the current {@link OptionalNamable}.
	 */
	public abstract ON removeName();
}
