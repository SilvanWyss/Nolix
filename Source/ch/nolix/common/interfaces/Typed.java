//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * A typed object has a type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface Typed {

	//abstract method
	/**
	 * @return the type of this typed object.
	 */
	public abstract String getType();
	
	//default method
	/**
	 * @param type
	 * @return true if this typed object has the given type.
	 */
	public default boolean hasType(final String type) {
		return getType().equals(type);
	}
}
