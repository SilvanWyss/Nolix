//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi;

//interface
/**
 * A {@link OptionalValued} can have a value.
 * 
 * @author Silvan Wyss
 * @date 2023-02-03
 * @param <V> is the type of the value of a {@link OptionalValued}.
 */
public interface OptionalValued<V> {
	
	//method declaration
	/**
	 * @return the value of the current {@link OptionalValued}.
	 */
	V getValue();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalValued} has a value.
	 */
	boolean hasValue();
	
	//method declaration
	/**
	 * @param value
	 * @return true if the current {@link OptionalValued} has the given value.
	 */
	boolean hasValue(String value);
}
