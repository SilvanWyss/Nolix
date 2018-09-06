//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * A {@link Valueable} has a certain value.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 50
 * @param <VA> The type of a {@link Valueable}.
 * @param <V> The type of the value of a {@link Valueable}.
 */
public interface Valueable<VA extends Valueable<VA, V>, V> {
	
	//abstract method
	/**
	 * @return the value of the current {@link Valueable}.
	 */
	public abstract V getValue();
	
	//default method
	/**
	 * @return a string representation of the value
	 * of the current {@link Valueable}.
	 */
	public default String getValueAsString() {
		return getValue().toString();
	}
	
	//default method
	/**
	 * @param value
	 * @return  true if the current {@link Valueable}
	 * has a value that equals the given value.
	 */
	public default boolean hasEqualValue(final Object value) {
		return getValue().equals(value);
	}
	
	//default method
	/**
	 * @param value
	 * @return true
	 * if the current {@link Valueable} has the given value.
	 */
	public default boolean hasValue(final Object value) {
		return (getValue() == value);
	}
	
	//abstract method
	/**
	 * Sets the value of the current {@link Valueable}.
	 * 
	 * @param value
	 * @return the current {@link Valueable}.
	 */
	public abstract VA setValue(V value);
}
