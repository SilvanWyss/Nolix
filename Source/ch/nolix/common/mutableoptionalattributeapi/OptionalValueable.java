//package declaration
package ch.nolix.common.mutableoptionalattributeapi;

//own imports
import ch.nolix.common.optionalattributeapi.OptionalValued;

//interface
/**
 * A {@link OptionalValueable} is a {@link OptionalValued} whose value can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 30
 * @param <OV> The type of a {@link OptionalValueable}.
 * @param <V> The type of the value of a {@link OptionalValueable}.
 */
public interface OptionalValueable<OV extends OptionalValueable<OV, V>, V> extends OptionalValued<V> {
	
	//method declaration
	/**
	 * Removes the value of the current {@link OptionalValueable}.
	 * 
	 * @return the current {@link OptionalValueable}.
	 */
	OV removeValue();
	
	//method declaration
	/**
	 * Sets the value of the current {@link OptionalValueable}.
	 * 
	 * @param value
	 * @return the current {@link OptionalValueable}.
	 */
	OV setValue(V value);
}
