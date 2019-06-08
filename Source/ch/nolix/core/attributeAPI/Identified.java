//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link Identified} has an id.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 60
 */
public interface Identified {
	
	//abstract method
	/**
	 * @return the id of the current {@link Identified}.
	 */
	public abstract long getId();
	
	//default method
	/**
	 * @return the id of the current {@link Identified} as {@link String}.
	 */
	public default String getIdAsString() {
		return String.valueOf(getId());
	}
	
	//default method
	/**
	 * @return the id of the current {@link Identified} as {@link String} in brackets.
	 */
	public default String getIdAsStringInBrackets() {
		return ("(" + getIdAsString() + ")");
	}
	
	//default method
	/**
	 * @return the id of the current {@link Identified} as {@link String} in quotes.
	 */
	public default String getIdAsStringInQuotes() {
		return ("'" + getIdAsString() + "'");
	}
	
	//default method
	/**
	 * @param id
	 * @return true if the current {@link Identified} has the given id.
	 */
	public default boolean hasId(final long id) {
		return (getId() == id);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Identified} has the same id as the given object.
	 */
	public default boolean hasSameIdAs(final Identified object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return hasId(object.getId());
	}
}
