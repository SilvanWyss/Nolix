//package declaration
package ch.nolix.common.mutableoptionalattributeapi;

//own imports
import ch.nolix.common.optionalattributeapi.OptionalIdentified;

//interface
/**
 * A {@link OptionalIdentified} is a {@link OptionalIdentified} whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 30
 * @param <OI> The type of a {@link OptionalIdentified}.
 */
public interface OptionalIdentifiable<OI extends OptionalIdentifiable<OI>> extends OptionalIdentified {
	
	//method declaration
	/**
	 * Removes the id of the current {@link OptionalIdentifiable}.
	 * 
	 * @return the current {@link OptionalIdentifiable}.
	 */
	OI removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link OptionalIdentifiable}.
	 * 
	 * @param id
	 * @return the current {@link OptionalIdentifiable}.
	 */
	OI setId(int id);
}
