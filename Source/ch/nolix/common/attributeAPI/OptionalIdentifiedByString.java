//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalIdentifiedByString} can have an id that is a {@link String}.
 * 
 * @author Silvan Wyss
 * @month 2020-01
 * @lines 40
 */
public interface OptionalIdentifiedByString {
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentifiedByString}.
	 */
	public abstract String getId();
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentifiedByString} in quotes.
	 */
	public default String getIdInQuotes() {
		return ("'" + getId() + "'");
	}
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalIdentifiedByString} has an id.
	 */
	public abstract boolean hasId();
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentifiedByString} has the given id.
	 */
	public default boolean hasId(final String id) {
		return (hasId() && getId() == id);
	}
}
