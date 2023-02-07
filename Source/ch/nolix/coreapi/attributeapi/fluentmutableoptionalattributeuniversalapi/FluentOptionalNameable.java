//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalNamed;

//interface
/**
 * A {@link FluentOptionalNameable} is a {@link OptionalNamed} whose name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <ON> is the type of a {@link FluentOptionalNameable}.
 */
public interface FluentOptionalNameable<ON extends FluentOptionalNameable<ON>> extends OptionalNamed {
	
	//method declaration
	/**
	 * Removes the name of the current {@link FluentOptionalNameable}.
	 * 
	 * @return the current {@link FluentOptionalNameable}.
	 */
	ON removeName();
	
	//method declaration
	/**
	 * Sets the name of the current {@link FluentOptionalNameable}.
	 * 
	 * @param name
	 * @return the current {@link FluentOptionalNameable}.
	 */
	ON setName(String name);
}
