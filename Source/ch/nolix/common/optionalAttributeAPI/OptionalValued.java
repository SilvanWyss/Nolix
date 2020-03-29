//package declaration
package ch.nolix.common.optionalAttributeAPI;

//interface
/**
 * A {@link OptionalValued} can have a value.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 100
 * @param <V> The type of the value of a {@link OptionalValued}.
 */
public interface OptionalValued<V> {
	
	//method declaration
	/**
	 * @return the value of the current {@link OptionalValued}.
	 * @throws Exception if the current {@link OptionalValued} does not have a value.
	 */
	public abstract V getValue();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalValued} has a value.
	 */
	public abstract boolean hasValue();
	
	//method
	/**
	 * @param value
	 * @return true if the current {@link OptionalValued} has a value that equals the given value.
	 */
	public default boolean hasEqualValue(final Object value) {
		
		//Handles the case that the current OptionalValued does not have a value.
		if (!hasValue()) {
			return false;
		}
		
		//Handles the case that the current OptionalValued has a value.
		return getValue().equals(value);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link OptionalValued} has an equal value as the given object.
	 */
	public default boolean hasEqualValueAs(final OptionalValued<?> object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a value.
		if (!object.hasValue()) {
			return false;
		}
		
		//Handles the case that the given object has a value.
		return hasEqualValue(object.getValue());
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link OptionalValued} has the same value as the given object.
	 */
	public default boolean hasSameValueAs(final OptionalValued<?> object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object does not have a value.
		if (!object.hasValue()) {
			return false;
		}
		
		//Handles the case that the given object has a value.
		return hasValue(object.getValue());
	}
	
	//method
	/**
	 * @param value
	 * @return true if the current {@link OptionalValued} has the given value.
	 */
	public default boolean hasValue(final Object value) {
		
		//Handles the case that the current OptionalValued does not have a value.
		if (!hasValue()) {
			return false;
		}
		
		//Handles the case that the current OptionalValued has a value.
		return (getValue() == value);
	}
}
