//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Valued} has a value.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 50
 * @param <VA> The type of a {@link Valued}.
 * @param <V> The type of the value of a {@link Valued}.
 */
public interface Valued<VA extends Valued<VA, V>, V> {
	
	//method declaration
	/**
	 * @return the value of the current {@link Valued}.
	 */
	V getValue();
	
	//method
	/**
	 * @return a {@link String} representation of the current {@link Valued}.
	 */
	default String getValueAsString() {
		return getValue().toString();
	}
	
	//method
	/**
	 * @param value
	 * @return true if the current {@link Valued} has a value that equals the given value.
	 */
	default boolean hasEqualValue(final Object value) {
		return getValue().equals(value);
	}
	
	//method
	/**
	 * @param value
	 * @return true if the current {@link Valued} has the given value.
	 */
	default boolean hasValue(final Object value) {
		return (getValue() == value);
	}
}
