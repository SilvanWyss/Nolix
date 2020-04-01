//package declaration
package ch.nolix.common.mutableOptionalAttributeAPI;

//own import
import ch.nolix.common.optionalAttributeAPI.OptionalIdentifiedByString;

//interface
/**
 * A {@link OptionalIdentifiableByString} is a {@link OptionalIdentifiableByString}
 * whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-01
 * @lines 30
 * @param <OIBS> The type of a {@link OptionalIdentifiableByString}.
 */
public interface OptionalIdentifiableByString<OIBS extends OptionalIdentifiableByString<OIBS>>
extends OptionalIdentifiedByString {
	
	//method declaration
	/**
	 * Removes the id of the current {@link OptionalIdentifiableByString}.
	 * 
	 * @return the current {@link OptionalIdentifiableByString}.
	 */
	public abstract OIBS removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link OptionalIdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link OptionalIdentifiableByString}.
	 */
	public abstract OIBS setId(String id);
}
