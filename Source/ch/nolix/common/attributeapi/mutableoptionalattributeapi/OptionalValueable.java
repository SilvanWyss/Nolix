//package declaration
package ch.nolix.common.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.common.attributeapi.optionalattributeapi.OptionalValued;

//interface
/**
 * A {@link OptionalValueable} is a {@link OptionalValued} whose value can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-10-01
 * @lines 30
 * @param <OV> is the type of a {@link OptionalValueable}.
 * @param <V> is the type of the value of a {@link OptionalValueable}.
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
