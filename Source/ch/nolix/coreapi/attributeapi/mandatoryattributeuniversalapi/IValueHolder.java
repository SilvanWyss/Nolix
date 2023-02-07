//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IValueHolder} has a value.
 * 
 * @author Silvan Wyss
 * @date 2018-09-09
 * @param <VA> is the type of a {@link IValueHolder}.
 * @param <V> is the type of the value of a {@link IValueHolder}.
 */
@AllowDefaultMethodsAsDesignPattern
public interface IValueHolder<V> {
	
	//method declaration
	/**
	 * @return the value of the current {@link IValueHolder}.
	 */
	V getValue();
	
	//method
	/**
	 * @return a {@link String} representation of the current {@link IValueHolder}.
	 */
	default String getValueAsString() {
		return getValue().toString();
	}
	
	//method
	/**
	 * @param value
	 * @return true if the current {@link IValueHolder} has a value that equals the given value.
	 */
	default boolean hasEqualValue(final Object value) {
		return getValue().equals(value);
	}
	
	//method
	/**
	 * @param value
	 * @return true if the current {@link IValueHolder} has the given value.
	 */
	default boolean hasValue(final Object value) {
		return (getValue() == value);
	}
}
