//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalNamable} can have a name that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 * @param <ON> The type of a {@link OptionalNamable}.
 */
public interface OptionalNamable<ON extends OptionalNamable<ON>> {
	
	//method declaration
	/**
	 * @return the name of the current {@link OptionalNamable}.
	 * @throws Exception if the current {@link OptionalNamable} does not have a name.
	 */
	public abstract String getName();
	
	//default method
	/**
	 * @return the name of the current {@link OptionalNamable} in quotes.
	 * @throws Exception if the current {@link OptionalNamable} does not have a name.
	 */
	public default String getNameInQuotes() {
		return ("'" + getName() + "'");
	}
	//method declaration
	/**
	 * @return true if the current {@link OptionalNamable} has a name.
	 */
	public abstract boolean hasName();
	
	//default method
	/**
	 * @param name
	 * @return true if the current {@link OptionalNamable} has the given name.
	 */
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
	
	//method declaration
	/**
	 * Removes the name of the current {@link OptionalNamable}.
	 * 
	 * @return the current {@link OptionalNamable}.
	 */
	public abstract ON removeName();
	
	//method declaration
	/**
	 * Sets the name of the current {@link OptionalNamable}.
	 * 
	 * @param name
	 * @return the current {@link OptionalNamable}.
	 */
	public abstract ON setName(String name);
}
