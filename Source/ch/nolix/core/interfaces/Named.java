//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A named object has a name.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
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
	
	//abstract method
	/**
	 * @param name
	 * @return true if this named object has the given name.
	 */
	public default boolean hasName(final String name) {
		return getName().equals(name);
	}
}
