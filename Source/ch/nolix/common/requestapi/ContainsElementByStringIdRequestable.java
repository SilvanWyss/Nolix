//package declaration
package ch.nolix.common.requestapi;

//interface
/**
* A {@link ContainsElementByStringIdRequestable} can be asked if it contains an element with a given id.
* 
* @author Silvan Wyss
* @date 2016-01-01
* @lines 20
*/
public interface ContainsElementByStringIdRequestable {
	
	//method declaration
	/**
	 * @param id
	 * @return true if the current {@link ContainsElementByStringIdRequestable} contains an element with the given id.
	 */
	boolean containsElement(String id);
}
