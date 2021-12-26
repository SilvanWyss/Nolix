//package declaration
package ch.nolix.common.requestapi;

//interface
/**
* A {@link MandatoryRequestable} can be asked if it is mandatory.
* 
* @author Silvan Wyss
* @date 2021-12-26
* @lines 20
*/
public interface MandatoryRequestable {
	
	//method declaration
	/**
	 * @return true if the current {@link MandatoryRequestable} is mandatory.
	 */
	boolean isMandatory();
	
	//method
	/**
	 * @return true if the current {@link MandatoryRequestable} is optional.
	 */
	default boolean isOptional() {
		return !isMandatory();
	}
}
