//package declaration
package ch.nolix.common.optionalAttributeAPI;

//interface
/**
 * A {@link OptionalIdentified} can have an id.
 * 
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 50
 */
public interface OptionalIdentified {
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentified}.
	 * @throws Exception if the current {@link OptionalIdentified} does not have an id.
	 */
	public abstract long getId();
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentified} as {@link String}.
	 * @throws Exception if the current {@link OptionalIdentified} does not have an id.
	 */
	public default String getIdAsString() {
		return String.valueOf(getId());
	}
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentified} in quotes.
	 * @throws Exception if the current {@link OptionalIdentified} does not have an id.
	 */
	public default String getIdInQuotes() {
		return ("'" + getIdAsString() + "'");
	}
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalIdentified} has an id.
	 */
	public abstract boolean hasId();
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentified} has the given id.
	 */
	public default boolean hasId(final long id) {
		return (hasId() && getId() == id);
	}
}
