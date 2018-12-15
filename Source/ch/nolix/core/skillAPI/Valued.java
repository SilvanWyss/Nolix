//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * A {@link Valued} has a certain value.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 50
 * @param <VA> The type of a {@link Valued}.
 * @param <V> The type of the value of a {@link Valued}.
 */
public interface Valued<VA extends Valued<VA, V>, V> {
	
	//abstract method
	/**
	 * @return the value of the current {@link Valued}.
	 */
	public abstract V getValue();
	
	//default method
	/**
	 * @return a string representation of the value
	 * of the current {@link Valued}.
	 */
	public default String getValueAsString() {
		return getValue().toString();
	}
	
	//default method
	/**
	 * @param value
	 * @return true if the current {@link Valued}
	 * has a value that equals the given value.
	 */
	public default boolean hasEqualValue(final Object value) {
		return getValue().equals(value);
	}
	
	//default method
	/**
	 * @param value
	 * @return true
	 * if the current {@link Valued} has the given value.
	 */
	public default boolean hasValue(final Object value) {
		return (getValue() == value);
	}
}
