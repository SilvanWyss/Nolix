//package declaration
package ch.nolix.common.optionalAttributeAPI;

//interface
/**
 * A {@link OptionalIdentified} can have an id that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 30
 */
public interface OptionalIdentifiable<OI extends OptionalIdentifiable<OI>> extends OptionalIdentified {
	
	//method declaration
	/**
	 * Removes the id of the current {@link OptionalIdentifiable}.
	 * 
	 * @return the current {@link OptionalIdentifiable}.
	 */
	public abstract OI removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link OptionalIdentifiable}.
	 * 
	 * @param id
	 * @return the current {@link OptionalIdentifiable}.
	 */
	public abstract OI setId(int id);
}
