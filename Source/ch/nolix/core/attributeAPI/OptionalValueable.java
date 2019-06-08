//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link OptionalValueable} is a {@link Valueable} whose value can be removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 60
 * @param <OV> The type of a {@link OptionalValueable}.
 * @param <V> The type of the value of a {@link OptionalValueable}.
 */
public interface OptionalValueable<OV extends OptionalValueable<OV, V>, V> extends Valueable<OV, V> {
	
	//abstract method
	/**
	 * @return true if the current {@link OptionalValueable} has a value.
	 */
	public abstract boolean hasValue();
	
	//default method
	/**
	 * @param value
	 * @return true if the current {@link OptionalValueable} has a value that equals the given value.
	 */
	@Override
	public default boolean hasEqualValue(final Object value) {
		
		//Handles the case that the current OptionalValueable does not have a value.
		if (!hasValue()) {
			return false;
		}
		
		//Handles the case that the current OptionalValueable has a value.
		return getValue().equals(value);
	}
	
	//default method
	/**
	 * @param value
	 * @return true if the current {@link OptionalValueable} has the given value.
	 */
	@Override
	public default boolean hasValue(final Object value) {
		
		//Handles the case that the current OptionalValueable does not have a value.
		if (!hasValue()) {
			return false;
		}
		
		//Handles the case that the current OptionalValueable has a value.
		return (getValue() == value);
	}
	
	//abstract method
	/**
	 * Removes the value of the current {@link OptionalValueable}.
	 * 
	 * @return the current {@link OptionalValueable}.
	 */
	public abstract OV removeValue();
}
