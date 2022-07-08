//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalIdentifiedByLong;

//interface
/**
 * A {@link OptionalIdentifiedByLong} is a {@link OptionalIdentifiedByLong} whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @param <OI> is the type of a {@link OptionalIdentifiedByLong}.
 */
public interface OptionalIdentifiable<OI extends OptionalIdentifiable<OI>> extends OptionalIdentifiedByLong {
	
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
