//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Named} has a name.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
public interface Named {
	
	//method declaration
	/**
	 * @return the name of the current {@link Named}.
	 */
	public abstract String getName();
	
	//method
	/**
	 * @return the name of the current {@link Named} in quotes.
	 */
	public default String getNameInQuotes() {
		return ("'" + getName() + "'");
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link Named} has the given name.
	 */
	public default boolean hasName(final String name) {
		return getName().equals(name);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link Named} has the same name as the given object.
	 */
	public default boolean hasSameNameAs(final Named object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return hasName(object.getName());
	}
}
