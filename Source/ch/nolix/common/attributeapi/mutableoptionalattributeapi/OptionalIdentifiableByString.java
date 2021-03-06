//package declaration
package ch.nolix.common.attributeapi.mutableoptionalattributeapi;

import ch.nolix.common.attributeapi.optionalattributeapi.OptionalIdentifiedByString;

//interface
/**
 * A {@link OptionalIdentifiableByString} is a {@link OptionalIdentifiableByString}
 * whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-01
 * @lines 30
 * @param <OIBS> is the type of a {@link OptionalIdentifiableByString}.
 */
public interface OptionalIdentifiableByString<OIBS extends OptionalIdentifiableByString<OIBS>>
extends OptionalIdentifiedByString {
	
	//method declaration
	/**
	 * Removes the id of the current {@link OptionalIdentifiableByString}.
	 * 
	 * @return the current {@link OptionalIdentifiableByString}.
	 */
	OIBS removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link OptionalIdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link OptionalIdentifiableByString}.
	 */
	OIBS setId(String id);
}
