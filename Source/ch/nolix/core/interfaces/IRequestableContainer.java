//package declaration
package ch.nolix.core.interfaces;

//interface
/**
* A requestable container is a container that:
* -Contains elements that can have a name.
* -Can be asked if it contains an element with a given name.
* 
* @author Silvan Wyss
* @month 2015-12
* @lines 20
*/
public interface IRequestableContainer {
	
	//abstract method
	/**
	 * @param name
	 * @return true if this requestable container
	 * contains an element with the given name.
	 */
	public abstract boolean containsElement(String name);
}
