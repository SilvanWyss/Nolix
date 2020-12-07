//package declaration
package ch.nolix.common.mutableoptionalattributeapi;

//own imports
import ch.nolix.common.optionalattributeapi.OptionalNamed;

//interface
/**
 * A {@link OptionalNamable} is a {@link OptionalNamed} whose name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <ON> The type of a {@link OptionalNamable}.
 */
public interface OptionalNamable<ON extends OptionalNamable<ON>> extends OptionalNamed {
	
	//method declaration
	/**
	 * Removes the name of the current {@link OptionalNamable}.
	 * 
	 * @return the current {@link OptionalNamable}.
	 */
	ON removeName();
	
	//method declaration
	/**
	 * Sets the name of the current {@link OptionalNamable}.
	 * 
	 * @param name
	 * @return the current {@link OptionalNamable}.
	 */
	ON setName(String name);
}
