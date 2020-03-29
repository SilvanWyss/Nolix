//package declaration
package ch.nolix.common.optionalAttributeAPI;

//interface
/**
 * A {@link OptionalIdentifiableByString} can have an id that:
 * -Is a {@link String}.
 * -Can be set and removed programmatically.
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
