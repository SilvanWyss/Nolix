//package declaration
package ch.nolix.common.requestAPI;

//interface
/**
* A {@link IRequestableContainer} is a container that:
* -Contains elements that can have an id.
* -Can be asked if it contains an element with a given id.
* 
* @author Silvan Wyss
* @month 2015-12
* @lines 20
*/
public interface IRequestableContainer {
	
	//method declaration
	/**
	 * @param id
	 * @return true if the current {@link IRequestableContainer} contains an element with the given id.
	 */
	public abstract boolean containsElement(String id);
}
