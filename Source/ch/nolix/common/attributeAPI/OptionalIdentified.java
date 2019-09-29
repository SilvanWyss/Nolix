//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalIdentified} can have an id.
 * 
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 50
 */
public interface OptionalIdentified {
	
	//abstract method
	/**
	 * @return the id of the current {@link OptionalIdentified}.
	 */
	public abstract long getId();
	
	//default method
	/**
	 * @return the id of the current {@link OptionalIdentified} as {@link String}.
	 */
	public default String getIdAsString() {
		return String.valueOf(getId());
	}
	
	//default method
	/**
	 * @return the id of the current {@link OptionalIdentified} in quotes.
	 */
	public default String getIdInQuotes() {
		return ("'" + getIdAsString() + "'");
	}
	
	//abstract method
	/**
	 * @return true if the current {@link OptionalIdentified} has an id.
	 */
	public abstract boolean hasId();
	
	//default method
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentified} has the given id.
	 */
	public default boolean hasId(final long id) {
		return (hasId() && getId() == id);
	}
}
