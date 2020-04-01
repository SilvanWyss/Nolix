//package declaration
package ch.nolix.common.optionalAttributeAPI;

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
	public abstract OV removeValue();
	
	//method declaration
	/**
	 * Sets the value of the current {@link OptionalValueable}.
	 * 
	 * @param value
	 * @return the current {@link OptionalValueable}.
	 */
	public abstract OV setValue(V value);
}
