//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalNamed;

//interface
/**
 * A {@link FluentOptionalNamable} is a {@link OptionalNamed} whose name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <ON> is the type of a {@link FluentOptionalNamable}.
 */
public interface FluentOptionalNamable<ON extends FluentOptionalNamable<ON>> extends OptionalNamed {
	
	//method declaration
	/**
	 * Removes the name of the current {@link FluentOptionalNamable}.
	 * 
	 * @return the current {@link FluentOptionalNamable}.
	 */
	ON removeName();
	
	//method declaration
	/**
	 * Sets the name of the current {@link FluentOptionalNamable}.
	 * 
	 * @param name
	 * @return the current {@link FluentOptionalNamable}.
	 */
	ON setName(String name);
}
