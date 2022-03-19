//package declaration
package ch.nolix.core.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.core.attributeapi.optionalattributeapi.OptionalIdentified;

//interface
/**
 * A {@link OptionalIdentified} is a {@link OptionalIdentified} whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @param <OI> is the type of a {@link OptionalIdentified}.
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
