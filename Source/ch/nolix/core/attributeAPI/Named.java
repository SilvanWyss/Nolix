//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A named object has a name.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public interface Named {
	
	//abstract method
	/**
	 * @return the name of this named object.
	 */
	public abstract String getName();
	
	//default method
	/**
	 * @return the name of this named object in quotes.
	 */
	public default String getNameInQuotes() {
		return ("'" + getName() + "'");
	}
	
	//default method
	/**
	 * @param name
	 * @return true if this named object has the given name.
	 */
	public default boolean hasName(final String name) {
		return getName().equals(name);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if this named object has the same name as the given object.
	 */
	public default boolean hasSameNameAs(final Named object) {
		return hasName(object.getName());
	}
}
