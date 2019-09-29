//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Identified} has an id.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 40
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
	 * @return the id of the current {@link Identified} in quotes.
	 */
	public default String getIdInQuotes() {
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
}
