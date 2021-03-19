//package declaration
package ch.nolix.common.requestapi;

//interface
/**
* A {@link OptionalityRequestable} can be asked if it is optional.
* 
* @author Silvan Wyss
* @date 2021-03-19
* @lines 20
*/
public interface OptionalityRequestable {
	
	//method
	/**
	 * @return true if the current {@link OptionalityRequestable} is mandatory.
	 */
	default boolean isMandatory() {
		return !isOptional();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalityRequestable} is optional.
	 */
	boolean isOptional();
}
