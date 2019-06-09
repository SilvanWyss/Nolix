//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link IdentifiedByString} has an id that is a {@link String}.
 * 
 * @author Silvan Wyss
 * @month 2019-06
 * @lines 30
 */
public interface IdentifiedByString {
	
	//abstract method
	/**
	 * @return the id of the current {@link IdentifiedByString}.
	 */
	public abstract String getId();
	
	//default method
	/**
	 * @return the id of the current {@link IdentifiedByString} in quotes.
	 */
	public default String getIdInQuotes() {
		return ("'" + getId() + "'");
	}
	
	//default method
	/**
	 * @param id
	 * @return true if the current {@link IdentifiedByString} has the given id. 
	 */
	public default boolean hasId(final String id) {
		return (getId() == id);
	}
}
