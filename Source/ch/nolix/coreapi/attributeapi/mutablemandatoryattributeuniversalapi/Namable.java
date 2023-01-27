//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;

//interface
/**
* A {@link Namable} is a {@link Named} whose name can be set programmatically.
* 
* @author Silvan Wyss
* @date 2023-01-27
*/
public interface Namable extends Named {
	
	//method declaration
	/**
	 * Sets the name of the current {@link Namable}.
	 * 
	 * @param name
	 * @throws RuntimeException if the given name is null.
	 */
	void setName(String name);
}
