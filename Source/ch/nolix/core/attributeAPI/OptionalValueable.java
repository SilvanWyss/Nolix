//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link OptionalValueable} can have a value that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 70
 * @param <OV> The type of a {@link OptionalValueable}.
 * @param <V> The type of the value of a {@link OptionalValueable}.
 */
public interface OptionalValueable<OV extends OptionalValueable<OV, V>, V> {
	
	//abstract method
	/**
	 * @return the value of the current {@link OptionalValueable}.
	 * @throws Exception if the current {@link OptionalValueable} does not have a value.
	 */
	public abstract V getValue();
	
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
	
	//abstract method
	/**
	 * Sets the value of the current {@link OptionalValueable}.
	 * 
	 * @param value
	 * @return the current {@link OptionalValueable}.
	 */
	public abstract OV setValue(V value);
}
