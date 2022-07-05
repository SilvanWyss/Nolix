//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeuniversalapi;

import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalIdentifiedByString;

//interface
/**
 * A {@link OptionalIdentifiableByString} is a {@link OptionalIdentifiableByString}
 * whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-02-01
 * @param <OIBS> is the type of a {@link OptionalIdentifiableByString}.
 */
public interface OptionalIdentifiableByString<OIBS extends OptionalIdentifiableByString<OIBS>>
extends OptionalIdentifiedByString {
	
	//method declaration
	/**
	 * Removes the id of the current {@link OptionalIdentifiableByString}.
	 */
	void removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link OptionalIdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link OptionalIdentifiableByString}.
	 */
	OIBS setId(String id);
}
