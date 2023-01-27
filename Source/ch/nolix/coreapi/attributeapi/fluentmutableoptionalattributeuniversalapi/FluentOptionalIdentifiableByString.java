//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalIdentifiedByString;

//interface
/**
 * A {@link FluentOptionalIdentifiableByString} is a {@link FluentOptionalIdentifiableByString}
 * whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-02-01
 * @param <OIBS> is the type of a {@link FluentOptionalIdentifiableByString}.
 */
public interface FluentOptionalIdentifiableByString<OIBS extends FluentOptionalIdentifiableByString<OIBS>>
extends OptionalIdentifiedByString {
	
	//method declaration
	/**
	 * Removes the id of the current {@link FluentOptionalIdentifiableByString}.
	 */
	void removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link FluentOptionalIdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link FluentOptionalIdentifiableByString}.
	 */
	OIBS setId(String id);
}
