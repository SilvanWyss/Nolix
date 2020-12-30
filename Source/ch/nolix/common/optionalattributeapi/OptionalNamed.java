//package declaration
package ch.nolix.common.optionalattributeapi;

//interface
/**
 * A {@link OptionalNamed} can have a name
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 70
 */
public interface OptionalNamed {
	
	//method declaration
	/**
	 * @return the name of the current {@link OptionalNamed}.
	 */
	String getName();
	
	//method
	/**
	 * @return the name of the current {@link OptionalNamed} in quotes.
	 */
	default String getNameInQuotes() {
		return ("'" + getName() + "'");
	}
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalNamed} has a name.
	 */
	boolean hasName();
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link OptionalNamed} has the given name.
	 */
	default boolean hasName(final String name) {
		
		//Handles the case that the current OptionalNamed does not have a name.
		if (!hasName()) {
			return false;
		}
		
		//Handles the case that the current OptionalNamed has a name.
		return getName().equals(name);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link OptionalNamed} has the same name as the given object.
	 */
	default boolean hasSameNameAs(final OptionalNamed object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a name.
		if (!object.hasName()) {
			return false;
		}
		
		//Handles the case that the given object has a name.
		return hasName(object.getName());
	}
}
