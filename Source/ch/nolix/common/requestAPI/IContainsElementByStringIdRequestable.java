//package declaration
package ch.nolix.common.requestAPI;

//interface
/**
* A {@link IContainsElementByStringIdRequestable} can be asked if it contains an element with a given id.
* 
* @author Silvan Wyss
* @month 2015-12
* @lines 20
*/
public interface IContainsElementByStringIdRequestable {
	
	//method declaration
	/**
	 * @param id
	 * @return true if the current {@link IContainsElementByStringIdRequestable} contains an element with the given id.
	 */
	public abstract boolean containsElement(String id);
}
