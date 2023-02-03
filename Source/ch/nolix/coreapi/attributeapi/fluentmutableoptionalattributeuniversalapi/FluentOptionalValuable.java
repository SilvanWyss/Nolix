//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalValued;

//interface
/**
 * A {@link FluentOptionalValuable} is a {@link OptionalValued} whose value can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-03
 * @param <FON> is the type of a {@link FluentOptionalValuable}.
 * @param <V> is the type of the value of a {@link FluentOptionalValuable}.
 */
public interface FluentOptionalValuable<
	FON extends FluentOptionalValuable<FON, V>,
	V
>
extends OptionalValued<V> {
	
	//method declaration
	/**
	 * Removes the value of the current {@link FluentOptionalValuable}.
	 * 
	 * @return the current {@link FluentOptionalValuable}.
	 */
	FON removeValue();
	
	//method declaration
	/**
	 * Sets the value of the current {@link FluentOptionalValuable}.
	 * 
	 * @param value
	 * @return the current {@link FluentOptionalValuable}.
	 * @throws RuntimeException if the given value is null.
	 */
	FON setValue(String value);
}
